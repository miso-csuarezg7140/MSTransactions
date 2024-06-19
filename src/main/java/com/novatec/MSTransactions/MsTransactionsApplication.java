package com.novatec.MSTransactions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsTransactionsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsTransactionsApplication.class, args);
    }
}
