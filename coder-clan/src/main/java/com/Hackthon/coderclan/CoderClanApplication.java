package com.Hackthon.coderclan;

import com.Hackthon.coderclan.Entity.EmailDetails;
import com.Hackthon.coderclan.Repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;


@SpringBootApplication
public class CoderClanApplication {
	public static void main(String[] args) {
		SpringApplication.run(CoderClanApplication.class, args);
	}
}
