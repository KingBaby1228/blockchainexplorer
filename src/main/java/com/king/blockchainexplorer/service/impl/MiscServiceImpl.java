package com.king.blockchainexplorer.service.impl;

import com.king.blockchainexplorer.service.MiscService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MiscServiceImpl implements MiscService {
    @Async          //异步
    @Override
    public void importFromHeight(Integer blockHeight, Boolean isClean) {

    }
    @Async          //异步
    @Override
    public void importFromHash(String blockHash, Boolean isClean) {

    }




}
