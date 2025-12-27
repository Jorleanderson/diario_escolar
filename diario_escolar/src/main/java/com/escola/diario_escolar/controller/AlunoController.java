package com.escola.diario_escolar.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.escola.diario_escolar.dto.AlunoDto;
import com.escola.diario_escolar.model.AlunoEntity;
import com.escola.diario_escolar.service.AlunoService;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
	
	private final AlunoService alunoService;

	public AlunoController(AlunoService service) {
		this.alunoService  = service;
	}
	
	@PostMapping
	public AlunoDto adicionarAluno(@RequestBody AlunoDto alunoDto) {
		
		return alunoService.criarAluno(alunoDto);
	}
	
	@GetMapping
	public List<AlunoEntity> listarTodos() {
		
		return alunoService.listarTodos();
		
	}
	
	@GetMapping("/{id}")
	public AlunoDto buscarPorId(@PathVariable("id") Long id) {
		
		return alunoService.buscarPorId(id);
	}
	
	@PutMapping("/{id}")
	public AlunoDto atualizar(@RequestBody AlunoDto aluno,
			@PathVariable("id") Long id) {
		
		return alunoService.atualizar(id,aluno);
	}
	
	@DeleteMapping("/{id}")
	public void deletarAluno(@PathVariable("id") Long id) {
		alunoService.deletar(id);
	}
}
