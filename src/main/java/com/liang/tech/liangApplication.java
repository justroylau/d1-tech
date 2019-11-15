package com.liang.tech;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//扫描dao  
@MapperScan("com.liang.tech.mapper")  
@SpringBootApplication
public class liangApplication{
	public static void main(String[] args) {
		SpringApplication.run(liangApplication.class, args);
	}

}
