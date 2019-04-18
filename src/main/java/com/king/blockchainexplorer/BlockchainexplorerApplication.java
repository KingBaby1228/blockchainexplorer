package com.king.blockchainexplorer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BlockchainexplorerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlockchainexplorerApplication.class, args);
	}

}
