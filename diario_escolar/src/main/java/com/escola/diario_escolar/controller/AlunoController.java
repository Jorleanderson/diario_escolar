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

import com.escola.diario_escolar.dto.AlunoDto;
import com.escola.diario_escolar.dto.AlunoPatchDto;
import com.escola.diario_escolar.service.AlunoService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

	private final AlunoService alunoService;

	public AlunoController(AlunoService service) {
		this.alunoService = service;
	}

	@PostMapping
	public ResponseEntity<AlunoDto> postAluno(
			@RequestBody @Valid AlunoDto alunoDto) {

		AlunoDto aluno = alunoService.criarAluno(alunoDto);

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(aluno.getId())
				.toUri();

		return ResponseEntity.created(location).body(aluno);
	}

	@GetMapping("/paginado")
	public ResponseEntity<Page<AlunoDto>> listarPaginado(
			@PageableDefault(size = 10, sort = "nome") Pageable pageable) {

		Page<AlunoDto> alunos = alunoService.listarPaginado(pageable);

		return ResponseEntity.ok(alunos);
	}

	@GetMapping
	public ResponseEntity<List<AlunoDto>> listarTodos() {
		List<AlunoDto> listaAlunos = alunoService.listarTodos();

		return ResponseEntity.ok(listaAlunos);
	}

	@PutMapping("/{id}")
	public ResponseEntity<AlunoDto> atualizar(
			@RequestBody @Valid AlunoDto alunoDto,
			@PathVariable("id") Long id) {

		AlunoDto alunoAtualizado = alunoService.atualizar(id, alunoDto);

		return ResponseEntity.ok(alunoAtualizado);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AlunoDto> buscarPorId(@PathVariable("id") Long id) {

		AlunoDto aluno = alunoService.buscarPorId(id);

		return ResponseEntity.ok(aluno);
	}

	@DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        alunoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

	@PatchMapping("/{id}")
	public ResponseEntity<AlunoDto> atualizarParcial(
			@PathVariable("id") Long id,
			@RequestBody AlunoPatchDto patchDto) {

		AlunoDto alunoAtualizado = alunoService.atualizarParcial(id, patchDto);

		return ResponseEntity.ok(alunoAtualizado);
	}
}
