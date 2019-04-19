package com.king.blockchainexplorer.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name="bitcoin",url = "http://localhost:18332")
public interface BitcoinApi {

    @GetMapping("/rest/chaininfo.json")
    JSONObject getChainInfo();

    @GetMapping("/rest/tx/{txhash}.json")
    JSONObject getTransaction(@PathVariable("txhash") String txhash);

    @GetMapping("/rest/block/{blockhash}.json")
    JSONObject getBlock(@PathVariable("blockhash") String blockhash);

    @GetMapping("/rest/block/notxdetails/{blockhash}.json")
    JSONObject getNoTxBlock(@PathVariable("blockhash") String blockhash);

    @GetMapping("/rest/headers/{count}/{blockhash}.json")
    JSONObject getBlockHeader(@PathVariable("count") Integer count ,@PathVariable("blockhash") String blockhash);

    @GetMapping("/rest/mempool/info.json")
    JSONObject getMempoolInfo();

    @GetMapping("/rest/mempool/contents.json")
    JSONObject getMempoolContents();

    //todo add getutxos api
}
