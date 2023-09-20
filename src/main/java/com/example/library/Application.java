package com.example.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * The main application class that serves as the entry point for the library application.
 */
@SpringBootApplication
@EnableJpaAuditing
public class Application {


	/**
	 * The main method to start the library application.
	 *
	 * @param args The command-line arguments (if any).
	 */

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
