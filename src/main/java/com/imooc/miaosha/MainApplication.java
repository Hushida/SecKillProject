package com.imooc.miaosha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by hushida on 18-4-15.
 */
@SpringBootApplication
//@EnableAutoConfiguration
//@ComponentScan({"com/imooc/miaosha"})
public class MainApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(MainApplication.class, args);
        //SpringApplication.run();
    }
}