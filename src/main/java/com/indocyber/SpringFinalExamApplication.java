package com.indocyber;

import com.indocyber.entity.Author;
import com.indocyber.entity.Book;
import com.indocyber.entity.Category;
import com.indocyber.service.AuthorService;
import com.indocyber.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringFinalExamApplication {


	public static void main(String[] args) {

		SpringApplication.run(SpringFinalExamApplication.class, args);

	}

	@Bean // akan berjalan ketika langsung dimulai
	public CommandLineRunner commandLineRunner(){

		return null;
	}

}
