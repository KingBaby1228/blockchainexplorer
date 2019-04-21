package com.king.blockchainexplorer.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;

@Service
public class BitcoinJsonRpcClient {
    private JsonRpcHttpClient jsonRpcHttpClient;

    public BitcoinJsonRpcClient() throws MalformedURLException {
        HashMap<String, String> headers = new HashMap<>();
        String authStrOrig = String.format("%s:%s","king","123456");
        String authStr = Base64.getEncoder().encodeToString(authStrOrig.getBytes());
        String authStrResult = String.format("Basic %s",authStr);
        headers.put("Authorization",authStrResult);
        jsonRpcHttpClient = new JsonRpcHttpClient(new URL("http://localhost:18332"),headers);
    }

    public String getBlockHashByHeight(Integer blockHeight) throws Throwable {
        String getblockhash = jsonRpcHttpClient.invoke("getblockhash", new Integer[]{blockHeight}, String.class);
        return getblockhash;
    }

    public Double getBlance(String address) throws Throwable {
        JSONArray blances = jsonRpcHttpClient.invoke("listunspent", new Object[]{6, 9999, new String[]{address}}, JSONArray.class);
        JSONObject blance = blances.getJSONObject(0);
        Double amount = blance.getDouble("amount");
        return amount;
    }

    public JSONObject getRawTransaxtion(String txid) throws Throwable {
        JSONObject rawTransaction = jsonRpcHttpClient.invoke("getrawtransaction", new Object[]{txid, true}, JSONObject.class);
        return rawTransaction;
    }

    public String getBestBlockhash() throws Throwable {
        String bestblockhash = jsonRpcHttpClient.invoke("getbestblockhash", new Object[]{}, String.class);
        return bestblockhash;
    }
}
