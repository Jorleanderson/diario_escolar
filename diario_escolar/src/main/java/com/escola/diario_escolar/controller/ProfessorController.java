package com.escola.diario_escolar.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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

import com.escola.diario_escolar.dto.ProfessorDTO;
import com.escola.diario_escolar.dto.ProfessorPatchDTO;
import com.escola.diario_escolar.service.ProfessorService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/professores")
public class ProfessorController {
	
	private final ProfessorService professorService;
	
	public ProfessorController(ProfessorService service) {
		this.professorService=service;
	}
	
	@PostMapping
	public ResponseEntity<ProfessorDTO> postProfessor(
			@RequestBody 
			@Valid ProfessorDTO dto) {
		ProfessorDTO professor = professorService.criarProfessor(dto);
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(professor);
	}
	
	@GetMapping("/paginado")
	public ResponseEntity<Page<ProfessorDTO>> listarPaginado(
	        @PageableDefault(size = 10, sort = "nome") Pageable pageable) {

	    Page<ProfessorDTO> pagina =
	            professorService.listarPaginado(pageable);

	    return ResponseEntity
	            .status(HttpStatus.OK)
	            .body(pagina);
	}

    @GetMapping
    public ResponseEntity<List<ProfessorDTO>> listarTodos() {
        List<ProfessorDTO> professor = professorService.listarTodos();
        
        return ResponseEntity
        		.status(HttpStatus.OK)
        		.body(professor);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ProfessorDTO> buscarPorId(@PathVariable("id") UUID id) {
        ProfessorDTO professor = professorService.buscarPorId(id);
        
        return ResponseEntity
        		.status(HttpStatus.OK)
        		.body(professor);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ProfessorDTO> atualizar(
            @PathVariable("id") UUID id,
            @RequestBody @Valid ProfessorDTO novoProfessorDTO) {
    	
    	ProfessorDTO professor = professorService.atualizar(id, novoProfessorDTO);
    	
    	return ResponseEntity.status(HttpStatus.OK).body(professor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") UUID id) {
    	 professorService.deletar(id);
    	 
    	 return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<ProfessorDTO> atualizarParcial(
            @PathVariable("id") UUID id,
            @RequestBody ProfessorPatchDTO patchDto) {

        ProfessorDTO atualizado = professorService.atualizarParcial(id, patchDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(atualizado);
    }
}
