package org.tttbiblioteca;

interface Gerenciavel {
    void adicionarItem(ItemBiblioteca item);
    void removerItem(ItemBiblioteca item) throws ItemNaoEncontradoException;
    ItemBiblioteca buscarItem(String titulo) throws ItemNaoEncontradoException;
    void listarItens();
}




