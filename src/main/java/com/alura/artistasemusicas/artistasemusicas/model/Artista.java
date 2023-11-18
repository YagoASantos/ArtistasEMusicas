package com.alura.artistasemusicas.artistasemusicas.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "artistas")
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, unique = true)
    private String nome;
    @Column(name = "tipo_artista")
    private TipoArtista tipo;
    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Musica> musicas;
    public Artista(){}
    public Artista(String nome, String tipo) {
        this.nome = nome;
        this.tipo = TipoArtista.convertToTipo(tipo);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoArtista getTipo() {
        return tipo;
    }

    public void setTipo(TipoArtista tipo) {
        this.tipo = tipo;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }

    @Override
    public String toString() {
        return  "id=" + id +
                ", nome='" + nome + '\'' +
                ", tipo=" + tipo;
    }
}
