package com.tttgerenciadorusuarios.springexercicio2.controller;

import com.tttgerenciadorusuarios.springexercicio2.model.Usuario;
import com.tttgerenciadorusuarios.springexercicio2.repositories.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Gerenciador de Usuários", description = "API para gerenciar usuários")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Operation(summary = "Retorna uma lista de todos os usuários")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @Operation(summary = "Cria um novo usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @PostMapping
    public Usuario createUsuario(@Parameter(description = "Usuário a ser criado", required = true) @RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Operation(summary = "Atualiza um usuário existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @PutMapping("/{id}")
    public Usuario updateUsuario(@Parameter(description = "ID do usuário a ser atualizado", required = true) @PathVariable Long id,
                                 @Parameter(description = "Detalhes do usuário atualizado", required = true) @RequestBody Usuario usuarioDetails) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();
        usuario.setNome(usuarioDetails.getNome());
        usuario.setEmail(usuarioDetails.getEmail());
        return usuarioRepository.save(usuario);
    }

    @Operation(summary = "Deleta um usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @DeleteMapping("/{id}")
    public void deleteUsuario(@Parameter(description = "ID do usuário a ser deletado", required = true) @PathVariable Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();
        usuarioRepository.delete(usuario);
    }
}
