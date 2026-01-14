package com.escola.diario_escolar.controller;

import java.net.URI;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

import com.escola.diario_escolar.dto.disciplina.DisciplinaDto;
import com.escola.diario_escolar.dto.disciplina.DisciplinaPatchDto;
import com.escola.diario_escolar.dto.disciplina.DisciplinaResponseDto;
import com.escola.diario_escolar.service.DisciplinaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Disciplinas", description = "Gerenciamento de disciplinas do sistema escolar")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/disciplinas")
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

    public DisciplinaController(DisciplinaService service) {
        this.disciplinaService = service;
    }

    @Operation(summary = "Cadastrar nova disciplina", description = "Cria uma nova disciplina no sistema e retorna os dados cadastrados.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Disciplina criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "409", description = "Disciplina já cadastrada")
    })
    @PostMapping
    public ResponseEntity<DisciplinaResponseDto> postDisciplina(
            @RequestBody @Valid DisciplinaDto dto) {
        DisciplinaResponseDto criado = disciplinaService.criarDisciplina(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(criado.getId())
                .toUri();

        return ResponseEntity.created(location).body(criado);
    }

    @Operation(summary = "Listar disciplinas paginadas", description = "Retorna uma lista paginada de disciplinas com suporte a paginação e ordenação.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    })
    @GetMapping("/paginado")
    public ResponseEntity<Page<DisciplinaDto>> listarPaginado(
            @Parameter(description = "Configuração de paginação e ordenação") @PageableDefault(size = 10, sort = "nome") Pageable pageable) {

        Page<DisciplinaDto> pagina = disciplinaService.listarPaginado(pageable);

        return ResponseEntity.ok(pagina);

    }

    @Operation(summary = "Listar todas as disciplinas", description = "Retorna todas as disciplinas cadastradas sem paginação.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    })
    @GetMapping
    public ResponseEntity<List<DisciplinaResponseDto>> listarTodos() {
        List<DisciplinaResponseDto> list = disciplinaService.listarTodos();

        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Buscar disciplina por ID", description = "Retorna os dados de uma disciplina pelo seu identificador.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Disciplina encontrada"),
            @ApiResponse(responseCode = "404", description = "Disciplina não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DisciplinaResponseDto> buscarPorId(
            @Parameter(description = "ID da disciplina", example = "1") @PathVariable("id") Long id) {

        DisciplinaResponseDto dto = disciplinaService.buscarPorId(id);

        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Atualizar disciplina", description = "Atualiza todos os dados de uma disciplina existente.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Disciplina atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Disciplina não encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<DisciplinaResponseDto> atualizar(
            @Parameter(description = "ID da disciplina") @PathVariable("id") Long id,
            @RequestBody @Valid DisciplinaDto disciplinaDto) {

        DisciplinaResponseDto atualizado = disciplinaService.atualizar(id, disciplinaDto);
        return ResponseEntity.ok(atualizado);
    }

    @Operation(summary = "Remover disciplina", description = "Remove uma disciplina pelo seu ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Disciplina removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Disciplina não encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @Parameter(description = "ID da disciplina") @PathVariable("id") Long id) {

        disciplinaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualizar parcialmente uma disciplina", description = "Atualiza apenas os campos informados da disciplina.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Disciplina atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Disciplina não encontrada")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<DisciplinaDto> atualizarParcial(
            @Parameter(description = "ID da disciplina") @PathVariable("id") Long id,
            @RequestBody DisciplinaPatchDto patchDto) {

        DisciplinaDto atualizado = disciplinaService.atualizarParcial(id, patchDto);
        return ResponseEntity.ok(atualizado);
    }
}