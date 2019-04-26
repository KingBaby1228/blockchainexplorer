package com.king.blockchainexplorer.service.impl;

import com.king.blockchainexplorer.dao.BlockMapper;
import com.king.blockchainexplorer.po.Block;
import com.king.blockchainexplorer.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BlockServiceImpl implements BlockService {

    @Autowired
    private BlockMapper blockMapper;

    @Override
    public List<Block> selectRecent() {
        List<Block> blocks = blockMapper.selectRecent();
        return blocks;
    }

    @Override
    public Block getBlockDetail(String blockhash) {
        Block block = blockMapper.selectByPrimaryKey(blockhash);
        return block;
    }
}
