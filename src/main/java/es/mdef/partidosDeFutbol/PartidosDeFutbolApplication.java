package es.mdef.partidosDeFutbol;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PartidosDeFutbolApplication {
	public static final Logger log = LoggerFactory.getLogger(PartidosDeFutbolApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(PartidosDeFutbolApplication.class, args);
	}

}
