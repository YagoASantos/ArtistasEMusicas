package com.alura.artistasemusicas.artistasemusicas.repository;

import com.alura.artistasemusicas.artistasemusicas.model.Artista;
import com.alura.artistasemusicas.artistasemusicas.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    Optional<Artista> findByNomeContainingIgnoreCase(String name);
    @Query("SELECT m FROM Artista a JOIN a.musicas m")
    List<Musica> findMusicas();
}
