package com.king.blockchainexplorer.service;

import com.king.blockchainexplorer.po.Block;

import java.util.List;

public interface BlockService {
    List<Block> selectRecent();

    Block getBlockDetail(String blockhash);
}
