package com.alura.artistasemusicas.artistasemusicas;

import com.alura.artistasemusicas.artistasemusicas.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ArtistasemusicasApplication implements CommandLineRunner {
	@Autowired
	ArtistaRepository artistaRepository;

	public static void main(String[] args) {
		SpringApplication.run(ArtistasemusicasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(artistaRepository);
		principal.run();
	}
}
