package org.tttbiblioteca;

class Periodico extends ItemBiblioteca {
    public Periodico(String titulo, String autor) {
        super(titulo, autor);
    }

    @Override
    public void emprestar() throws ItemNaoDisponivelException {
        if (!disponivel) {
            throw new ItemNaoDisponivelException("O periódico " + titulo + " não está disponível para empréstimo.");
        }
        disponivel = false;
        System.out.println("Periódico " + titulo + " emprestado com sucesso.");
    }

    @Override
    public void devolver() {
        disponivel = true;
        System.out.println("Periódico " + titulo + " devolvido com sucesso.");
    }
}
