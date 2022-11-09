package com.spring.kafka.chatapp.SpringKafkaChatApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;


@EnableKafka
@SpringBootApplication
public class SpringKafkaChatAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringKafkaChatAppApplication.class, args);
	}

}
