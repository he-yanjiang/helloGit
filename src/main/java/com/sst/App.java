package com.sst;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 *
 */
@EnableCaching
@SpringBootApplication(scanBasePackages = {"com.sst"})
@MapperScan(basePackages = {"com.sst.mapper"})
@RestController
public class App 
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class,args);
    }
}
