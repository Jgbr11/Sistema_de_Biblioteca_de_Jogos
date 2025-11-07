package br.pucpr;

public class Jogo {
    private String titulo;
    private String plataforma;
    private String ano;
    private String genero;

    public Jogo(String titulo, String plataforma, String genero, String ano) {
        this.titulo = titulo;
        this.plataforma = plataforma;
        this.genero = genero;
        this.ano = ano;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return String.format("Título: %s | Plataforma: %s | Gênero: %s | Ano: %s",
                titulo, plataforma, genero, ano);
    }
}