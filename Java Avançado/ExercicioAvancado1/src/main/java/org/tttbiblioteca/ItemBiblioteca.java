package org.tttbiblioteca;

abstract class ItemBiblioteca {
    protected String titulo;
    protected String autor;
    protected boolean disponivel;

    public ItemBiblioteca(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
        this.disponivel = true;
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public abstract void emprestar() throws ItemNaoDisponivelException;

    public abstract void devolver();

    @Override
    public String toString() {
        return "Título: " + titulo + ", Autor: " + autor + ", Disponível: " + disponivel;
    }
}


