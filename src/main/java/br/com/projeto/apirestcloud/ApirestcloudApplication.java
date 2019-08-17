package br.com.projeto.apirestcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class ApirestcloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApirestcloudApplication.class, args);
	}

}
