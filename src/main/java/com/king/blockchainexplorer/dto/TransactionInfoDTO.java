package com.king.blockchainexplorer.dto;

import java.util.Date;
import java.util.List;

public class TransactionInfoDTO {
    private String txid;

    private String txhash;

    private Long size;

    private Date time;

    private Integer weight;

    private Double totalOutput;

    private Double totalInput;

    private Double fees;

    private List<TxDetailInTxInfo> txDetailInTxInfos;

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public String getTxhash() {
        return txhash;
    }

    public void setTxhash(String txhash) {
        this.txhash = txhash;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Double getTotalOutput() {
        return totalOutput;
    }

    public void setTotalOutput(Double totalOutput) {
        this.totalOutput = totalOutput;
    }

    public Double getTotalInput() {
        return totalInput;
    }

    public void setTotalInput(Double totalInput) {
        this.totalInput = totalInput;
    }

    public Double getFees() {
        return fees;
    }

    public void setFees(Double fees) {
        this.fees = fees;
    }

    public List<TxDetailInTxInfo> getTxDetailInTxInfos() {
        return txDetailInTxInfos;
    }

    public void setTxDetailInTxInfos(List<TxDetailInTxInfo> txDetailInTxInfos) {
        this.txDetailInTxInfos = txDetailInTxInfos;
    }
}
