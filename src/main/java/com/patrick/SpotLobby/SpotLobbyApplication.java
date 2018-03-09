package com.patrick.SpotLobby;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EntityScan(basePackages = { "com.patrick.SpotLobby.Beans" })
@EnableJpaRepositories(basePackages = { "com.patrick.SpotLobby.DAO" })
public class SpotLobbyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpotLobbyApplication.class, args);
	}

}
