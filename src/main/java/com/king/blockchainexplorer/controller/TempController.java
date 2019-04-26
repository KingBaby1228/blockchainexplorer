package com.king.blockchainexplorer.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.king.blockchainexplorer.api.BitcoinApi;
import com.king.blockchainexplorer.api.BitcoinJsonRpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temp")
public class TempController {

    @Autowired
    private BitcoinApi bitcoinApi;

    @Autowired
    private BitcoinJsonRpcClient bitcoinJsonRpcClient;

    @GetMapping("/test")
    public void test() throws Throwable {
        /*JSONObject chainInfo = bitcoinApi.getChainInfo();
        String txhash="c613c6c41223aede621b763f4e8d134d6f4092cc395fee294be0eb7cf634a19a";
        JSONObject transaction = bitcoinApi.getTransaction(txhash);
        String blockhash="0000000000005f8bcdcb535716b1b50d363958feb924ea5727b91f18f9f7827c";   //命令getblockbesthash-->blockhash
        JSONObject block = bitcoinApi.getBlock(blockhash);
        JSONObject noTxBlock = bitcoinApi.getNoTxBlock(blockhash);
        String blockhash2="00000000002b45f27bb6666fda51152b60349ef2d54124d9a1ce2c84585962b6";   //命令getblockhash  + blocks  ->blockhash
        JSONArray blockHeader = bitcoinApi.getBlockHeader(10, blockhash2);
        JSONObject mempoolInfo = bitcoinApi.getMempoolInfo();
        JSONObject mempoolContents = bitcoinApi.getMempoolContents();
        String blockByHeight = bitcoinJsonRpcClient.getBlockHashByHeight(894064);*/


//        String address = "2NFURWSwUEY8Hj92LBKVYafgpNxUYtqQarT";          //listunspent
//        JSONObject blance = bitcoinJsonRpcClient.getBlance(address);

        String txid = "c613c6c41223aede621b763f4e8d134d6f4092cc395fee294be0eb7cf634a19a";
        JSONObject rawTransaxtion = bitcoinJsonRpcClient.getRawTransaxtion(txid);
    }
}
