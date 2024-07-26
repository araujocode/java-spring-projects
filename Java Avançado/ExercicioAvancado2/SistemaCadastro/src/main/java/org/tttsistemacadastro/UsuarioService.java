package org.tttsistemacadastro;

import org.tttsistemacadastro.exceptions.InvalidUserDataException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class UsuarioService {
    private List<Usuario> usuarios = new ArrayList<>();
    private static class EmailValidator {
        private static final String EMAIL_PATTERN = "^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,}$";
        private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

        public boolean validar(String email) {
            return email != null && pattern.matcher(email).matches();
        }
    }

    public void adicionarUsuario(Usuario usuario) throws InvalidUserDataException {
        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            throw new InvalidUserDataException("Nome inválido");
        }
        if (usuario.getEmail() == null || !new EmailValidator().validar(usuario.getEmail())) {
            throw new InvalidUserDataException("Email inválido");
        }
        if (usuario.getIdade() <= 0) {
            throw new InvalidUserDataException("Idade inválida");
        }
        usuarios.add(usuario);
    }

    public List<Usuario> buscarUsuariosPorNome(String regex) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        return usuarios.stream()
                .filter(usuario -> pattern.matcher(usuario.getNome()).find())
                .collect(Collectors.toList());
    }

    public List<Usuario> buscarUsuariosPorEmail(String regex) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        return usuarios.stream()
                .filter(usuario -> pattern.matcher(usuario.getEmail()).find())
                .collect(Collectors.toList());
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
}
