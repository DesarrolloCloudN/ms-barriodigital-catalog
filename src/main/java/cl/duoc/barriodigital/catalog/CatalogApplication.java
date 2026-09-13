package cl.duoc.barriodigital.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Esta es la clase que arranca el microservicio de "catalog" (tipos de
// trámite y cupos). @SpringBootApplication le dice a Spring que configure
// todo automáticamente.
@SpringBootApplication
public class CatalogApplication {

	// Punto de entrada del programa: acá empieza a correr la aplicación.
	public static void main(String[] args) {
		SpringApplication.run(CatalogApplication.class, args);
	}

}
