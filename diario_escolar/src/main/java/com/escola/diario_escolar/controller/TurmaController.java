package com.escola.diario_escolar.controller;

import java.net.URI;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;

import com.escola.diario_escolar.dto.aluno.AlunoResponseDto;
import com.escola.diario_escolar.dto.turma.TurmaDto;
import com.escola.diario_escolar.dto.turma.TurmaPatchDto;
import com.escola.diario_escolar.service.AlunoService;
import com.escola.diario_escolar.service.TurmaService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
    name = "Turmas",
    description = "Gerenciamento de turmas e alunos vinculados"
)
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/turmas")
public class TurmaController {

    private final TurmaService turmaService;
    private final AlunoService alunoService;

    public TurmaController(TurmaService turmaService, AlunoService alunoService) {
        this.turmaService = turmaService;
        this.alunoService = alunoService;
    }

    // -------------------- TURMAS --------------------

    @Operation(
        summary = "Criar nova turma",
        description = "Cria uma nova turma no sistema."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Turma criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "409", description = "Turma já cadastrada")
    })
    @PostMapping
    public ResponseEntity<TurmaDto> criarTurma(
            @RequestBody @Valid TurmaDto dto) {

        TurmaDto turmaCriada = turmaService.criarTurma(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(turmaCriada.getId())
                .toUri();

        return ResponseEntity.created(location).body(turmaCriada);
    }

    @Operation(
        summary = "Listar todas as turmas",
        description = "Retorna todas as turmas cadastradas sem paginação."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    })
    @GetMapping
    public ResponseEntity<List<TurmaDto>> listarTodos() {

        List<TurmaDto> turmas = turmaService.listarTodos();
        return ResponseEntity.ok(turmas);
    }

    @Operation(
        summary = "Listar turmas paginadas",
        description = "Retorna uma lista paginada de turmas com suporte a paginação e ordenação."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    })
    @GetMapping("/paginado")
    public ResponseEntity<Page<TurmaDto>> listarPaginado(
            @Parameter(description = "Configuração de paginação e ordenação")
            @PageableDefault(size = 10, sort = "nome") Pageable pageable) {

        Page<TurmaDto> turmas = turmaService.listarPaginado(pageable);
        return ResponseEntity.ok(turmas);
    }

    @Operation(
        summary = "Buscar turma por ID",
        description = "Retorna os dados de uma turma pelo seu identificador."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Turma encontrada"),
        @ApiResponse(responseCode = "404", description = "Turma não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TurmaDto> buscarPorId(
            @Parameter(description = "ID da turma", example = "1")
            @PathVariable("id") Long id) {

        TurmaDto turma = turmaService.buscarPorId(id);
        return ResponseEntity.ok(turma);
    }

    @Operation(
        summary = "Atualizar turma",
        description = "Atualiza todos os dados de uma turma existente."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Turma atualizada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "404", description = "Turma não encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TurmaDto> atualizar(
            @Parameter(description = "ID da turma")
            @PathVariable("id") Long id,
            @RequestBody @Valid TurmaDto dto) {

        TurmaDto turmaAtualizada = turmaService.atualizar(dto, id);
        return ResponseEntity.ok(turmaAtualizada);
    }

    @Operation(
        summary = "Remover turma",
        description = "Remove uma turma pelo seu ID."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Turma removida com sucesso"),
        @ApiResponse(responseCode = "404", description = "Turma não encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @Parameter(description = "ID da turma")
            @PathVariable("id") Long id) {

        turmaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
        summary = "Atualizar parcialmente uma turma",
        description = "Atualiza apenas os campos informados da turma."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Turma atualizada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "404", description = "Turma não encontrada")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<TurmaDto> atualizarParcial(
            @Parameter(description = "ID da turma")
            @PathVariable("id") Long id,
            @RequestBody TurmaPatchDto patchDto) {

        TurmaDto turmaAtualizada = turmaService.atualizarParcial(id, patchDto);
        return ResponseEntity.ok(turmaAtualizada);
    }

    @Operation(
        summary = "Listar alunos da turma",
        description = "Retorna todos os alunos vinculados a uma turma."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Turma não encontrada")
    })
    @GetMapping("/{turmaId}/alunos")
    public ResponseEntity<List<AlunoResponseDto>> listarAlunosDaTurma(
            @Parameter(description = "ID da turma")
            @PathVariable Long turmaId) {

        return ResponseEntity.ok(alunoService.listarPorTurma(turmaId));
    }

    @Operation(
        summary = "Listar alunos da turma (paginado)",
        description = "Retorna os alunos de uma turma de forma paginada."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Turma não encontrada")
    })
    @GetMapping("/{turmaId}/alunos/paginado")
    public ResponseEntity<Page<AlunoResponseDto>> listarAlunosDaTurmaPaginado(
            @Parameter(description = "ID da turma")
            @PathVariable Long turmaId,
            @Parameter(description = "Configuração de paginação")
            @PageableDefault(size = 5) Pageable pageable) {

        return ResponseEntity.ok(
                alunoService.listarPorTurmaPaginado(turmaId, pageable));
    }

}
