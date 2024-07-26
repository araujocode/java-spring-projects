package com.apirest.demo.controller;

import com.apirest.demo.model.Tarefa;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {
    private List<Tarefa> tarefas = new ArrayList<>();
    private static long nextId = 1; // Variável estática para o próximo ID

    @Operation(summary = "Obter todas as tarefas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefas encontradas",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Tarefa[].class)) })
    })
    @GetMapping
    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    @Operation(summary = "Adicionar uma nova tarefa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa adicionada",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Tarefa.class)) })
    })
    @PostMapping
    public Tarefa addTarefa(@RequestBody Tarefa tarefa) {
        tarefa.setId(nextId++); // Atribui e incrementa o próximo ID
        tarefas.add(tarefa);
        return tarefa;
    }

    @Operation(summary = "Atualizar uma tarefa existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa atualizada",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Tarefa.class)) }),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> updateTarefa(@PathVariable long id, @RequestBody Tarefa updatedTarefa) {
        for (Tarefa tarefa : tarefas) {
            if (tarefa.getId() == id) {
                tarefa.setDescricao(updatedTarefa.getDescricao());
                tarefa.setCompleta(updatedTarefa.isCompleta());
                return ResponseEntity.ok(tarefa);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Operation(summary = "Deletar uma tarefa existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tarefa deletada"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTarefa(@PathVariable long id) {
        boolean removed = tarefas.removeIf(t -> t.getId() == id);
        if (removed) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
