package com.elsevier.elsevierauthorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ElsevierAuthorServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(ElsevierAuthorServiceApplication.class, args);
  }
}
