package com.patrick.SpotLobby;

import org.hibernate.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.hibernate.SessionFactory;
import javax.persistence.*;
import org.springframework.context.annotation.*;


@SpringBootApplication
public class SpotLobbyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpotLobbyApplication.class, args);
	}

}
