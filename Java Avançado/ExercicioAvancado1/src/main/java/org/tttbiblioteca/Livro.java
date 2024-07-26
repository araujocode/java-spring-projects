package org.tttbiblioteca;

class Livro extends ItemBiblioteca {
    public Livro(String titulo, String autor) {
        super(titulo, autor);
    }

    @Override
    public void emprestar() throws ItemNaoDisponivelException {
        if (!disponivel) {
            throw new ItemNaoDisponivelException("O livro " + titulo + " não está disponível para empréstimo.");
        }
        disponivel = false;
        System.out.println("Livro " + titulo + " emprestado com sucesso.");
    }

    @Override
    public void devolver() {
        disponivel = true;
        System.out.println("Livro " + titulo + " devolvido com sucesso.");
    }
}




