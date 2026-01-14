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

import com.escola.diario_escolar.dto.professor.ProfessorDto;
import com.escola.diario_escolar.dto.professor.ProfessorPatchDto;
import com.escola.diario_escolar.service.ProfessorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

@Tag(
    name = "Professores",
    description = "Gerenciamento de professores do sistema escolar"
)
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/professores")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService service) {
        this.professorService = service;
    }

    @Operation(
        summary = "Cadastrar novo professor",
        description = "Cria um novo professor no sistema e retorna os dados cadastrados."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Professor criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "409", description = "Professor já cadastrado")
    })
    @PostMapping
    public ResponseEntity<ProfessorDto> postProfessor(
            @RequestBody @Valid ProfessorDto dto) {

        ProfessorDto professor = professorService.criarProfessor(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(professor.getId())
                .toUri();

        return ResponseEntity.created(location).body(professor);
    }

    @Operation(
        summary = "Listar professores paginados",
        description = "Retorna uma lista paginada de professores com suporte a paginação e ordenação."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    })
    @GetMapping("/paginado")
    public ResponseEntity<Page<ProfessorDto>> listarPaginado(
            @Parameter(description = "Configuração de paginação e ordenação")
            @PageableDefault(size = 10, sort = "nome") Pageable pageable) {

        Page<ProfessorDto> pagina = professorService.listarPaginado(pageable);
        return ResponseEntity.ok(pagina);
    }

    @Operation(
        summary = "Listar todos os professores",
        description = "Retorna todos os professores cadastrados sem paginação."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    })
    @GetMapping
    public ResponseEntity<List<ProfessorDto>> listarTodos() {

        List<ProfessorDto> professores = professorService.listarTodos();
        return ResponseEntity.ok(professores);
    }

    @Operation(
        summary = "Buscar professor por ID",
        description = "Retorna os dados de um professor pelo seu identificador."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Professor encontrado"),
        @ApiResponse(responseCode = "404", description = "Professor não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProfessorDto> buscarPorId(
            @Parameter(description = "ID do professor", example = "1")
            @PathVariable("id") Long id) {

        ProfessorDto professor = professorService.buscarPorId(id);
        return ResponseEntity.ok(professor);
    }

    @Operation(
        summary = "Atualizar professor",
        description = "Atualiza todos os dados de um professor existente."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Professor atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "404", description = "Professor não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProfessorDto> atualizar(
            @Parameter(description = "ID do professor")
            @PathVariable("id") Long id,
            @RequestBody @Valid ProfessorDto professorDto) {

        ProfessorDto professor = professorService.atualizar(id, professorDto);
        return ResponseEntity.ok(professor);
    }

    @Operation(
        summary = "Remover professor",
        description = "Remove um professor pelo seu ID."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Professor removido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Professor não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @Parameter(description = "ID do professor")
            @PathVariable("id") Long id) {

        professorService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
        summary = "Atualizar parcialmente um professor",
        description = "Atualiza apenas os campos informados do professor."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Professor atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "404", description = "Professor não encontrado")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<ProfessorDto> atualizarParcial(
            @Parameter(description = "ID do professor")
            @PathVariable("id") Long id,
            @RequestBody ProfessorPatchDto patchDto) {

        ProfessorDto professorAtualizado = professorService.atualizarParcial(id, patchDto);
        return ResponseEntity.ok(professorAtualizado);
    }
}
