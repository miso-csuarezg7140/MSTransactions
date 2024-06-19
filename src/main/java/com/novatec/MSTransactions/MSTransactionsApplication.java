package com.novatec.MSTransactions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MSTransactionsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MSTransactionsApplication.class, args);
    }
}
