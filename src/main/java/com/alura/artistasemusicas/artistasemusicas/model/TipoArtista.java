package com.alura.artistasemusicas.artistasemusicas.model;

public enum TipoArtista {
    SOLO("solo"),
    DUPLA("dupla"),
    Banda("banda");
    private String tipoArtista;
    TipoArtista(String tipo) {
        this.tipoArtista = tipo;
    }

    public static TipoArtista convertToTipo(String tipoArtista) {
        for(TipoArtista tipo : TipoArtista.values()) {
            if(tipo.tipoArtista.equalsIgnoreCase(tipoArtista)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Nenhum tipo de artista encontrado para a string fornecida." + tipoArtista);
    }
}
