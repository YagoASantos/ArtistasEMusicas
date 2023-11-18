package com.alura.artistasemusicas.artistasemusicas;

import com.alura.artistasemusicas.artistasemusicas.model.Artista;
import com.alura.artistasemusicas.artistasemusicas.model.Musica;
import com.alura.artistasemusicas.artistasemusicas.model.TipoArtista;
import com.alura.artistasemusicas.artistasemusicas.repository.ArtistaRepository;
import org.hibernate.ObjectNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ArtistaRepository artistaRepository;

    public Principal(ArtistaRepository artistaRepository) {
        this.artistaRepository = artistaRepository;
    }

    public void run() {
        var escolha = -1;
        while(escolha != 0) {
            System.out.println("""
                    *** Scrren Sound Músicas ***
                    1 - Cadastrar artista
                    2 - Cadastrar músicas
                    3 - Listar músicas
                    4 - Buscar músicas por artistas
                    5 - Pesquisar dados sobre um artista
                    
                    0 - Sair
                    """);
            escolha = leitura.nextInt();
            switch (escolha) {
                case 1:
                    cadastrarArtista();
                break;
                case 2:
                    cadastrarMusica();
                break;
                case 3:
                    listarMusicas();
                break;
                case 4:
                    listarMusicasPorArtista();
                break;
            }
        }
    }

    private void cadastrarArtista() {
        String escolha = "s";
        while (!escolha.equalsIgnoreCase("n")) {
            System.out.println("Insira o nome do artista: ");
            leitura.nextLine();
            var nomeArtista = leitura.nextLine();
            System.out.println("Insira o tipo do artista: (solo, dupla, banda)");
            var tipoArtista = leitura.next();
            Artista artista = new Artista(nomeArtista, tipoArtista);
            try {
                artistaRepository.save(artista);
            } catch(Exception ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println(artista);
            System.out.println("Cadastrar outro artista? (S/N)");
            escolha = leitura.next();
        }
    }

    private void cadastrarMusica() {
        var escolha = "s";
        List<Musica> musicas = new ArrayList<>();
        listarArtistas();
        leitura.nextLine();
        System.out.println("Digite o nome do artista da música: ");
        var nomeArtista = leitura.nextLine();
        Optional<Artista> artista = artistaRepository.findByNomeContainingIgnoreCase(nomeArtista);
        if(artista.isPresent()) {
            while(!escolha.equalsIgnoreCase("n")) {
                System.out.println("Digite o nome da música:");
                var nomeMusica = leitura.nextLine();
                Musica musica = new Musica(nomeMusica, artista.get());
                musicas.add(musica);
                System.out.println("Deseja cadastrar mais músicas? (S/N)");
                escolha = leitura.nextLine();
            }
            artista.get().setMusicas(musicas);
            artistaRepository.save(artista.get());
        } else {
            System.out.println("Artista não encontrado.");
        }
    }

    private void listarMusicas() {
        try {
            List<Musica> musicas = artistaRepository.findMusicas();
            musicas.forEach(musica -> {
                System.out.printf("Nome da música: %s; Nome do artista: %s%n", musica.getNome(),
                        musica.getArtista().getNome());

            });
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
    private void listarMusicasPorArtista() {
        leitura.nextLine();
        System.out.println("Digite o nome do artista: ");
        var nomeArtista = leitura.nextLine();
        try {
            Artista artista = encontrarArtista(nomeArtista);
            artista.getMusicas().forEach(System.out::println);
        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
        }

    }
    private Artista encontrarArtista(String nomeArtista) {
        Optional<Artista> artistaBuscado = artistaRepository.findByNomeContainingIgnoreCase(nomeArtista);
        if(artistaBuscado.isPresent()) {
            return artistaBuscado.get();
        }
        throw new RuntimeException("Artista não encontrado.");
    }
    private void listarArtistas() {
        try {
            List<Artista> artistas = artistaRepository.findAll();
            artistas.forEach(System.out::println);
        } catch(RuntimeException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
