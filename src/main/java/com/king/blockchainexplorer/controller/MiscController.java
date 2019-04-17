package com.king.blockchainexplorer.controller;

import com.king.blockchainexplorer.dto.ImportStateDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/misc")
public class MiscController {

    @GetMapping("/search")
    public Object search(@RequestParam String keyword){
        return null;
    }

    /**
     * 同步系列
     */
    @GetMapping("/importFromHeight")
    public void importFromHeight(@RequestParam Integer blockHeight, Boolean isClean){//是否需要清空

    }

    @GetMapping("/importFromHash")
    public void importFromHash(@RequestParam String blockhash,Boolean isClean){

    }

    @GetMapping("/getImportState")
    public ImportStateDTO getImportState(){
        return null;
    }
}
