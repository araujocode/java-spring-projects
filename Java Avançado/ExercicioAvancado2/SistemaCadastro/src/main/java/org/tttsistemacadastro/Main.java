package org.tttsistemacadastro;

import org.tttsistemacadastro.exceptions.InvalidUserDataException;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UsuarioService usuarioService = new UsuarioService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Adicionar Usuário");
            System.out.println("2. Buscar Usuário por Nome");
            System.out.println("3. Buscar Usuário por Email");
            System.out.println("4. Listar Todos Usuários");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    System.out.print("ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();

                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    System.out.print("Idade: ");
                    int idade = scanner.nextInt();
                    scanner.nextLine();

                    try {
                        Usuario usuario = new Usuario(id, nome, email, idade);
                        usuarioService.adicionarUsuario(usuario);
                        System.out.println("Usuário adicionado com sucesso!");
                    } catch (InvalidUserDataException e) {
                        System.out.println("Erro ao adicionar usuário: " + e.getMessage());
                    }
                    break;

                case "2":
                    System.out.print("Digite o nome completo ou parcialmente: ");
                    String regexNome = scanner.nextLine();
                    List<Usuario> usuariosPorNome = usuarioService.buscarUsuariosPorNome(regexNome);
                    if (usuariosPorNome.isEmpty()) {
                        System.out.println("Nenhum usuário encontrado com esse nome.");
                    } else {
                        usuariosPorNome.forEach(System.out::println);
                    }
                    break;

                case "3":
                    System.out.print("Digite o email completo ou parcialmente: ");
                    String regexEmail = scanner.nextLine();
                    List<Usuario> usuariosPorEmail = usuarioService.buscarUsuariosPorEmail(regexEmail);
                    if (usuariosPorEmail.isEmpty()) {
                        System.out.println("Nenhum usuário encontrado com esse email.");
                    } else {
                        usuariosPorEmail.forEach(System.out::println);
                    }
                    break;

                case "4":
                    List<Usuario> todosUsuarios = usuarioService.getUsuarios();
                    if (todosUsuarios.isEmpty()) {
                        System.out.println("Não há usuários cadastrados.");
                    } else {
                        System.out.println("Lista de Todos Usuários:");
                        todosUsuarios.forEach(System.out::println);
                    }
                    break;

                case "5":
                    System.out.println("Saindo...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
