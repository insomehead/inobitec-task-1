package com.headly.firstinobitec;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.headly.firstinobitec.repository")
public class FirstInobitecApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstInobitecApplication.class, args);
	}

}
