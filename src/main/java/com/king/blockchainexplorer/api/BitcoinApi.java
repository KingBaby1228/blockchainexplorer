package com.king.blockchainexplorer.api;

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
}
