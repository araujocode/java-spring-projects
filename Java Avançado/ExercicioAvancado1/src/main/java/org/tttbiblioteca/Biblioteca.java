package org.tttbiblioteca;

import java.util.ArrayList;
import java.util.Scanner;

class Biblioteca implements Gerenciavel {
    private ArrayList<ItemBiblioteca> itens;

    public Biblioteca() {
        itens = new ArrayList<>();
    }

    @Override
    public void adicionarItem(ItemBiblioteca item) {
        itens.add(item);
        System.out.println("Item " + item.getTitulo() + " adicionado à biblioteca.");
    }

    @Override
    public void removerItem(ItemBiblioteca item) throws ItemNaoEncontradoException {
        if (!itens.remove(item)) {
            throw new ItemNaoEncontradoException("Item " + item.getTitulo() + " não encontrado na biblioteca.");
        }
        System.out.println("Item " + item.getTitulo() + " removido da biblioteca.");
    }

    @Override
    public ItemBiblioteca buscarItem(String titulo) throws ItemNaoEncontradoException {
        for (ItemBiblioteca item : itens) {
            if (item.getTitulo().equalsIgnoreCase(titulo)) {
                return item;
            }
        }
        throw new ItemNaoEncontradoException("Item " + titulo + " não encontrado na biblioteca.");
    }

    @Override
    public void listarItens() {
        System.out.println("Itens na biblioteca:");
        for (ItemBiblioteca item : itens) {
            System.out.println(item);
        }
    }

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Bem-vindo à Biblioteca!");
            System.out.println("Você é um: ");
            System.out.println("1. Bibliotecário");
            System.out.println("2. Cliente");
            System.out.println("3. Sair");
            int usuario = scanner.nextInt();
            scanner.nextLine();

            if (usuario == 1) {
                while (true) {
                    System.out.println("Escolha uma opção:");
                    System.out.println("1. Adicionar item");
                    System.out.println("2. Remover item");
                    System.out.println("3. Listar itens da biblioteca");
                    System.out.println("4. Voltar");
                    int opcao = scanner.nextInt();
                    scanner.nextLine();

                    try {
                        switch (opcao) {
                            case 1:
                                System.out.println("Escolha o tipo de item:");
                                System.out.println("1. Livro");
                                System.out.println("2. Periódico");
                                System.out.println("3. Mídia Digital");
                                int tipo = scanner.nextInt();
                                scanner.nextLine();

                                System.out.println("Digite o título:");
                                String titulo = scanner.nextLine();

                                System.out.println("Digite o autor:");
                                String autor = scanner.nextLine();

                                ItemBiblioteca item = null;
                                switch (tipo) {
                                    case 1:
                                        item = new Livro(titulo, autor);
                                        break;
                                    case 2:
                                        item = new Periodico(titulo, autor);
                                        break;
                                    case 3:
                                        item = new MidiaDigital(titulo, autor);
                                        break;
                                    default:
                                        System.out.println("Tipo inválido.");
                                        continue;
                                }
                                biblioteca.adicionarItem(item);
                                break;
                            case 2:
                                System.out.println("Digite o título do item para remover:");
                                titulo = scanner.nextLine();
                                ItemBiblioteca itemParaRemover = biblioteca.buscarItem(titulo);
                                biblioteca.removerItem(itemParaRemover);
                                break;
                            case 3:
                                biblioteca.listarItens();
                                break;
                            case 4:
                                break;
                            default:
                                System.out.println("Opção inválida.");
                                break;
                        }
                    } catch (ItemNaoEncontradoException e) {
                        System.out.println(e.getMessage());
                    }
                    if (opcao == 4) {
                        break;
                    }
                }
            } else if (usuario == 2) {
                while (true) {
                    System.out.println("Escolha uma opção:");
                    System.out.println("1. Emprestar item");
                    System.out.println("2. Devolver item");
                    System.out.println("3. Listar itens da biblioteca");
                    System.out.println("4. Voltar");
                    int opcao = scanner.nextInt();
                    scanner.nextLine();

                    try {
                        switch (opcao) {
                            case 1:
                                System.out.println("Digite o título do item para emprestar:");
                                String titulo = scanner.nextLine();
                                ItemBiblioteca itemParaEmprestar = biblioteca.buscarItem(titulo);
                                itemParaEmprestar.emprestar();
                                break;
                            case 2:
                                System.out.println("Digite o título do item para devolver:");
                                titulo = scanner.nextLine();
                                ItemBiblioteca itemParaDevolver = biblioteca.buscarItem(titulo);
                                itemParaDevolver.devolver();
                                break;
                            case 3:
                                biblioteca.listarItens();
                                break;
                            case 4:
                                break;
                            default:
                                System.out.println("Opção inválida.");
                                break;
                        }
                    } catch (ItemNaoDisponivelException | ItemNaoEncontradoException e) {
                        System.out.println(e.getMessage());
                    }
                    if (opcao == 4) {
                        break;
                    }
                }
            } else if (usuario == 3) {
                scanner.close();
                System.out.println("Saindo...");
                return;
            } else {
                System.out.println("Tipo de usuário inválido.");
            }
        }
    }
}




