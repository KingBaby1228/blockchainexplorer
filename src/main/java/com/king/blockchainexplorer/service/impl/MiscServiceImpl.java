package com.king.blockchainexplorer.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.king.blockchainexplorer.api.BitcoinApi;
import com.king.blockchainexplorer.api.BitcoinJsonRpcClient;
import com.king.blockchainexplorer.dao.BlockMapper;
import com.king.blockchainexplorer.dao.TransactionDetailMapper;
import com.king.blockchainexplorer.dao.TransactionMapper;
import com.king.blockchainexplorer.enumeration.TransactionDetailType;
import com.king.blockchainexplorer.po.Block;
import com.king.blockchainexplorer.po.Transaction;
import com.king.blockchainexplorer.po.TransactionDetail;
import com.king.blockchainexplorer.service.MiscService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MiscServiceImpl implements MiscService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BitcoinApi bitcoinApi;

    @Autowired
    private BitcoinJsonRpcClient bitcoinJsonRpcClient;

    @Autowired
    private BlockMapper blockMapper;

    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private TransactionDetailMapper transactionDetailMapper;

    @Async          //异步
    @Override
    public void importFromHeight(Integer blockHeight, Boolean isClean) {

    }
    @Async          //异步    同步
    @Override
    public void importFromHash(String blockHash, Boolean isClean) throws Throwable {
       if (isClean){
           blockMapper.truncate();
           transactionMapper.truncate();
           transactionDetailMapper.truncate();
       }
       String temphash = blockHash;

       while (temphash !=null && !temphash.isEmpty()) {
           JSONObject blockOrigin = bitcoinApi.getBlock(temphash);
           Block block = new Block();
           block.setBlockhash(blockOrigin.getString("hash"));
           block.setBlockchainId(2);
           block.setHeight(blockOrigin.getInteger("height"));
           Long time = blockOrigin.getLong("time");
           Date date = new Date(time * 1000);
           block.setTime(date);
           JSONArray txes = blockOrigin.getJSONArray("tx");
           for (int i=0;i<txes.size();i++){
             importTx(txes.getJSONObject(i),temphash,date);
           }
           block.setTxSize(txes.size());
           block.setSizeOnDisk(blockOrigin.getLong("size"));
           block.setDifficulty(blockOrigin.getDouble("difficulty"));
           block.setPrevBlockhash(blockOrigin.getString("previousblockhash"));
           block.setNextBlockhash(blockOrigin.getString("nextblockhash"));
           block.setMerkleRoot(blockOrigin.getString("merkleroot"));
           blockMapper.insert(block);

           temphash = blockOrigin.getString("nextblockhash");

       }

         logger.info("sync finished");
    }

    public void importTx(JSONObject tx,String blockhash,Date time) throws Throwable {
        Transaction transaction = new Transaction();
        String txid = tx.getString("txid");
        transaction.setTxid(txid);
        transaction.setTxhash(tx.getString("hash"));
        transaction.setBlockhash(blockhash);
        transaction.setSize(tx.getLong("size"));
        transaction.setWeight(tx.getInteger("weight"));
        transaction.setWeight(tx.getInteger("weight"));
        transaction.setTime(time);
        transactionMapper.insert(transaction);

        JSONArray vouts = tx.getJSONArray("vout");
        for (int i=0;i<vouts.size();i++){
           importVoutDetail(vouts.getJSONObject(i),txid);
        }

        JSONArray vins = tx.getJSONArray("vin");

        //todo vin0 coinbase tx

        for (int i=1;i<vins.size();i++){
            importVinDetail(vins.getJSONObject(i),txid);
        }

    }


    public void importVoutDetail(JSONObject vout,String txid){
        TransactionDetail transactionDetail = new TransactionDetail();
        transactionDetail.setTxid(txid);
        JSONObject scriptPubKey = vout.getJSONObject("scriptPubKey");
        JSONArray addresses = scriptPubKey.getJSONArray("address");
        //todo chech whether sync address
        if (addresses !=null && !addresses.isEmpty()){
            String address = addresses.getString(0);
            transactionDetail.setAddress(address);
        }
        Double amount = vout.getDouble("value");
        transactionDetail.setAmount(amount);
        transactionDetail.setType((byte) TransactionDetailType.Receive.ordinal());

        transactionDetailMapper.insert(transactionDetail);
    }

    public void importVinDetail(JSONObject vin,String txidOrigin) throws Throwable {
        String txid = vin.getString("txid");
        Integer vout = vin.getInteger("vout");

        JSONObject rawTransaxtion = bitcoinJsonRpcClient.getRawTransaxtion(txid);
        JSONArray vouts = rawTransaxtion.getJSONArray("vout");
        JSONObject jsonObject = vouts.getJSONObject(vout);

        TransactionDetail transactionDetail = new TransactionDetail();
        transactionDetail.setTxid(txidOrigin);
        transactionDetail.setType((byte) TransactionDetailType.Send.ordinal());
        Double amout = jsonObject.getDouble("value");
        transactionDetail.setAmount(amout);
        JSONObject scriptPubKey = jsonObject.getJSONObject("scriptPubKey");
        JSONArray addresses = scriptPubKey.getJSONArray("address");
        if (addresses!=null){
            String address = addresses.getString(0);
            transactionDetail.setAddress(address);
        }
        transactionDetailMapper.insert(transactionDetail);
    }




}
