package com.escola.diario_escolar.controller;

import java.net.URI;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;

import org.springframework.http.ResponseEntity;
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

import com.escola.diario_escolar.dto.aluno.AlunoDto;
import com.escola.diario_escolar.dto.aluno.AlunoPatchDto;
import com.escola.diario_escolar.dto.aluno.AlunoResponseDto;
import com.escola.diario_escolar.service.AlunoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Alunos", description = "Gerenciamento de alunos")
@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

	private final AlunoService alunoService;

	public AlunoController(AlunoService service) {
		this.alunoService = service;
	}

	@PostMapping
	public ResponseEntity<AlunoResponseDto> postAluno(
			@RequestBody @Valid AlunoDto alunoDto) {

		AlunoResponseDto aluno = alunoService.criarAluno(alunoDto);

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(aluno.getId())
				.toUri();

		return ResponseEntity.created(location).body(aluno);
	}

	@GetMapping("/paginado")
	public ResponseEntity<Page<AlunoResponseDto>> listarPaginado(
			@PageableDefault(size = 10, sort = "nome") Pageable pageable) {

		Page<AlunoResponseDto> alunos = alunoService.listarPaginado(pageable);

		return ResponseEntity.ok(alunos);
	}

	@GetMapping
	public ResponseEntity<List<AlunoResponseDto>> listarTodos() {
		List<AlunoResponseDto> listaAlunos = alunoService.listarTodos();

		return ResponseEntity.ok(listaAlunos);
	}

	@PutMapping("/{id}")
	public ResponseEntity<AlunoResponseDto> atualizar(
			@RequestBody @Valid AlunoDto alunoDto,
			@PathVariable("id") Long id) {

		AlunoResponseDto alunoAtualizado = alunoService.atualizar(id, alunoDto);
		return ResponseEntity.ok(alunoAtualizado);
	}

	@Operation(
		summary = "Buscar aluno por ID",
		description = "Retorna os dados completos de um aluno pelo seu identificador"
	)
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Aluno encontrado"),
		@ApiResponse(responseCode = "404", description = "Aluno não encontrado")
	})
	@GetMapping("/{id}")
	public ResponseEntity<AlunoResponseDto> buscarPorId(@PathVariable("id") Long id) {

		AlunoResponseDto aluno = alunoService.buscarPorId(id);

		return ResponseEntity.ok(aluno);
	}

	@DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        alunoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

	@PatchMapping("/{id}")
	public ResponseEntity<AlunoResponseDto> atualizarParcial(
			@PathVariable("id") Long id,
			@RequestBody AlunoPatchDto patchDto) {

		AlunoResponseDto alunoAtualizado = alunoService.atualizarParcial(id, patchDto);
		return ResponseEntity.ok(alunoAtualizado);
	}
}
