package com.sakura.study;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sakura.study.dao")
public class StudyabroadServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyabroadServerApplication.class, args);
	}

}
