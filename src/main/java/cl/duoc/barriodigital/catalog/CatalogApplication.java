package cl.duoc.barriodigital.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Arranca el microservicio de catalog (tipos de trámite y cupos).
@SpringBootApplication
public class CatalogApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogApplication.class, args);
	}

}
