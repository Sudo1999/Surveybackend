package survey.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/*
L'annotation @SpringBootApplication est une encapsulation de trois annotations :
    @Configuration qui permet à la classe de remplacer les traditionnels fichiers XML par des configurations via des Beans;
    @EnableAutoConfiguration qui permet de générer les configurations nécessaires en fonction des dépendances du classpath;
    @ComponentScan qui indique qu'il faut scanner les classes du package afin de trouver des Beans de configuration.*/
public class BackendApplication {		// On appelle normalement l'api sur le port 8080

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
}
