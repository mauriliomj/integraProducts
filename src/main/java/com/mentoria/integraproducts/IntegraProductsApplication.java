package com.mentoria.integraproducts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class IntegraProductsApplication {

  public static void main(String[] args) {
    SpringApplication.run(IntegraProductsApplication.class, args);
  }

}
