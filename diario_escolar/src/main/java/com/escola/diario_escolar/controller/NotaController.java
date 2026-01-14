package com.escola.diario_escolar.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.escola.diario_escolar.dto.nota.NotaDto;
import com.escola.diario_escolar.dto.nota.NotaPatchDto;
import com.escola.diario_escolar.dto.nota.NotaResponseDto;
import com.escola.diario_escolar.service.NotaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

@Tag(
    name = "Notas",
    description = "Gerenciamento de notas dos alunos"
)
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/notas")
public class NotaController {

    private final NotaService notaService;

    public NotaController(NotaService service) {
        this.notaService = service;
    }

    @Operation(
        summary = "Cadastrar nova nota",
        description = "Cria uma nova nota vinculada a um aluno e a uma disciplina."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Nota criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "404", description = "Aluno ou disciplina não encontrados")
    })
    @PostMapping
    public ResponseEntity<NotaResponseDto> postNota(
            @RequestBody @Valid NotaDto nota) {

        NotaResponseDto notaCriada = notaService.criarNota(nota);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(notaCriada.getId())
                .toUri();

        return ResponseEntity.created(location).body(notaCriada);
    }

    @Operation(
        summary = "Listar todas as notas",
        description = "Retorna todas as notas cadastradas no sistema."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    })
    @GetMapping
    public ResponseEntity<List<NotaDto>> listarTodos() {

        List<NotaDto> notas = notaService.listarTodos();
        return ResponseEntity.ok(notas);
    }

    @Operation(
        summary = "Buscar nota por ID",
        description = "Retorna os dados de uma nota pelo seu identificador."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Nota encontrada"),
        @ApiResponse(responseCode = "404", description = "Nota não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<NotaDto> buscarPorId(
            @Parameter(description = "ID da nota", example = "1")
            @PathVariable("id") Long id) {

        NotaDto nota = notaService.buscarPorId(id);
        return ResponseEntity.ok(nota);
    }

    @Operation(
        summary = "Atualizar nota",
        description = "Atualiza todos os dados de uma nota existente."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Nota atualizada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "404", description = "Nota não encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<NotaDto> atualizar(
            @Parameter(description = "ID da nota")
            @PathVariable("id") Long id,
            @RequestBody @Valid NotaDto notaDto) {

        NotaDto nota = notaService.atualizar(id, notaDto);
        return ResponseEntity.ok(nota);
    }

    @Operation(
        summary = "Remover nota",
        description = "Remove uma nota pelo seu ID."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Nota removida com sucesso"),
        @ApiResponse(responseCode = "404", description = "Nota não encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @Parameter(description = "ID da nota")
            @PathVariable("id") Long id) {

        notaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
        summary = "Atualizar parcialmente uma nota",
        description = "Atualiza apenas os campos informados da nota."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Nota atualizada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "404", description = "Nota não encontrada")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<NotaDto> atualizarParcial(
            @Parameter(description = "ID da nota")
            @PathVariable("id") Long id,
            @RequestBody NotaPatchDto patchDto) {

        NotaDto notaAtualizada = notaService.atualizarParcial(id, patchDto);
        return ResponseEntity.ok(notaAtualizada);
    }
}
