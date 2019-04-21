package com.king.blockchainexplorer.dao;

import com.king.blockchainexplorer.po.TransactionDetail;
import com.king.blockchainexplorer.po.TransactionDetailKey;

public interface TransactionDetailMapper {
    int deleteByPrimaryKey(TransactionDetailKey key);

    int truncate();

    int insert(TransactionDetail record);

    int insertSelective(TransactionDetail record);

    TransactionDetail selectByPrimaryKey(TransactionDetailKey key);

    int updateByPrimaryKeySelective(TransactionDetail record);

    int updateByPrimaryKey(TransactionDetail record);
}