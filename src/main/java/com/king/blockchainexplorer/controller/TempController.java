package com.king.blockchainexplorer.controller;

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
        JSONObject chainInfo = bitcoinApi.getChainInfo();
        String txhash="8b92109ce1df7e81e7811e15031d564f77f2f99fffb4e211e8a1faadbe101696";
        JSONObject transaction = bitcoinApi.getTransaction(txhash);
        String blockhash="00000000000016adcd353e5e424a6b5b390f8918c3fd4c16b539a405279ac0ed";
        JSONObject block = bitcoinApi.getBlock(blockhash);
        JSONObject noTxBlock = bitcoinApi.getNoTxBlock(blockhash);
        String blockhash2="000000000000077c5dccdab0aab6dbb6dccbd585a9e0aa6909b78944c114274f";
//        JSONObject blockHeader = bitcoinApi.getBlockHeader(10, blockhash2);
        JSONObject mempoolInfo = bitcoinApi.getMempoolInfo();
        JSONObject mempoolContents = bitcoinApi.getMempoolContents();
        String blockByHeight = bitcoinJsonRpcClient.getBlockHashByHeight(894064);
//        String address = "";
//        JSONObject blance = bitcoinJsonRpcClient.getBlance(address);   //todo address 0418
    }
}
