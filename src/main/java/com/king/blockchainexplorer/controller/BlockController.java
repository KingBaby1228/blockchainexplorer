package com.king.blockchainexplorer.controller;

import com.king.blockchainexplorer.api.BitcoinApi;
import com.king.blockchainexplorer.api.BitcoinJsonRpcClient;
import com.king.blockchainexplorer.dao.BlockMapper;
import com.king.blockchainexplorer.dto.BlockDetailDTO;
import com.king.blockchainexplorer.dto.BlockListDTO;
import com.king.blockchainexplorer.po.Block;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/block")
@CrossOrigin
public class BlockController {

    @Autowired
    private BitcoinApi bitcoinApi;

    @Autowired
    private BitcoinJsonRpcClient bitcoinJsonRpcClient;

    @Autowired
    private BlockMapper blockMapper;


    @GetMapping("/getRecentBlocks")
    public List<BlockListDTO> getRecentBlocks(){
        List<Block> blocks = blockMapper.selectRecent();
        List<BlockListDTO> blockListDTOS = blocks.stream().map(block -> {
            BlockListDTO blockListDTO = new BlockListDTO();
            blockListDTO.setHeight(block.getHeight());
            blockListDTO.setTime(block.getTime().getTime());    //时间--->long
            blockListDTO.setTxSize(block.getTxSize());
            blockListDTO.setSizeOnDisk(block.getSizeOnDisk());
            return blockListDTO;
        }).collect(Collectors.toList());

        return blockListDTOS;
    }


    /*通过blockchainId得到block*/
    @GetMapping("/getRecentBlocksById")
    public List<BlockListDTO> getRecentBlocksById(@RequestParam Integer blockchainId){
      return null;
    }
    /*通过name和type得到block*/
    @GetMapping("/getRecentBlocksByNameType")
    public List<BlockListDTO> getRecentBlocksByNameType(@RequestParam String name,
                                                        @RequestParam String type){
        return null;
    }
    /*通过hash获取对应的block具体内容*/
    @GetMapping("/getBlockDetailByHash")
    public BlockDetailDTO getBlockDetailByHash(@RequestParam String blockhash){
        Block block = blockMapper.selectByPrimaryKey(blockhash);
        BlockDetailDTO blockDetailDTO = new BlockDetailDTO(block);
        return blockDetailDTO;
    }
    /*通过height获取对应的block具体内容*/
    @GetMapping("/getBlockDetailByHeight")
    public BlockDetailDTO getBlockDetailByHeight(@RequestParam Integer blockheight){
        BlockDetailDTO blockDetailByHeight = blockMapper.getBlockDetailByHeight(blockheight);
        return blockDetailByHeight;
    }
}
