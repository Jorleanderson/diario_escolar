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

import com.escola.diario_escolar.dto.NotaDto;
import com.escola.diario_escolar.dto.NotaPatchDto;
import com.escola.diario_escolar.service.NotaService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/notas")
public class NotaController {

	private final NotaService notaService;

	public NotaController(NotaService service) {
		this.notaService = service;
	}

	@PostMapping
	public ResponseEntity<NotaDto> postNota(
			@RequestBody @Valid NotaDto nota) {
		NotaDto notaCriada = notaService.criarNota(nota);

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(notaCriada.getId())
				.toUri();

		return ResponseEntity.created(location).body(notaCriada);
	}

	@GetMapping("/paginado")
	public ResponseEntity<Page<NotaDto>> listarPaginado(
			@PageableDefault(size = 10, sort = "id") Pageable pageable) {

		Page<NotaDto> pagina = notaService.listarPaginado(pageable);

		return ResponseEntity.ok(pagina);

	}

	@GetMapping
	public ResponseEntity<List<NotaDto>> listarTodos() {
		List<NotaDto> notas = notaService.listarTodos();

		return ResponseEntity.ok(notas);
	}

	@GetMapping("/{id}")
	public ResponseEntity<NotaDto> buscarPorId(
			@PathVariable("id") Long id) {

		NotaDto nota = notaService.buscarPorId(id);

		return ResponseEntity.ok(nota);
	}

	@PutMapping("/{id}")
	public ResponseEntity<NotaDto> atualizar(
			@PathVariable("id") Long id,
			@RequestBody @Valid NotaDto notaDto) {

		NotaDto nota = notaService.atualizar(id, notaDto);

		return ResponseEntity.ok(nota);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		notaService.deletar(id);

		return ResponseEntity.noContent().build();
	}

	@PatchMapping("/{id}")
	public ResponseEntity<NotaDto> atualizarParcial(
			@PathVariable("id") Long id,
			@RequestBody NotaPatchDto patchDto) {

		NotaDto notaAtualizada = notaService.atualizarParcial(id, patchDto);

		return ResponseEntity.ok(notaAtualizada);
	}

}
