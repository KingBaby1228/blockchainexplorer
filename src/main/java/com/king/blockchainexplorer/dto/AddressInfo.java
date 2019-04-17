package com.king.blockchainexplorer.dto;

public class AddressInfo {
    private String address;
    private String hash160;
    private Integer txSize;
    private Double totalReceived;
    private Double finalBlance;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHash160() {
        return hash160;
    }

    public void setHash160(String hash160) {
        this.hash160 = hash160;
    }

    public Integer getTxSize() {
        return txSize;
    }

    public void setTxSize(Integer txSize) {
        this.txSize = txSize;
    }

    public Double getTotalReceived() {
        return totalReceived;
    }

    public void setTotalReceived(Double totalReceived) {
        this.totalReceived = totalReceived;
    }

    public Double getFinalBlance() {
        return finalBlance;
    }

    public void setFinalBlance(Double finalBlance) {
        this.finalBlance = finalBlance;
    }
}
