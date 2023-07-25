package com.springboot.todolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.springboot.todolist.repository.UserRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class SpringbootToDoListApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootToDoListApplication.class, args);
	}

}
