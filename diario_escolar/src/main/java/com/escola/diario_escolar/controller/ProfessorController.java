package com.escola.diario_escolar.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

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
        this.professorService = service;
    }

    @PostMapping
    public ResponseEntity<ProfessorDTO> postProfessor(
            @RequestBody @Valid ProfessorDTO dto) {
        ProfessorDTO professor = professorService.criarProfessor(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(professor.getId())
                .toUri();

        return ResponseEntity.created(location).body(professor);
    }

    @GetMapping("/paginado")
    public ResponseEntity<Page<ProfessorDTO>> listarPaginado(
            @PageableDefault(size = 10, sort = "nome") Pageable pageable) {

        Page<ProfessorDTO> pagina = professorService.listarPaginado(pageable);

        return ResponseEntity.ok(pagina);

    }

    @GetMapping
    public ResponseEntity<List<ProfessorDTO>> listarTodos() {
        List<ProfessorDTO> professores = professorService.listarTodos();

        return ResponseEntity.ok(professores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorDTO> buscarPorId(
            @PathVariable("id") UUID id) {

        ProfessorDTO professor = professorService.buscarPorId(id);

        return ResponseEntity.ok(professor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorDTO> atualizar(
            @PathVariable("id") UUID id,
            @RequestBody @Valid ProfessorDTO professorDto) {

        ProfessorDTO professor = professorService.atualizar(id, professorDto);

        return ResponseEntity.ok(professor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") UUID id) {
        professorService.deletar(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProfessorDTO> atualizarParcial(
            @PathVariable("id") UUID id,
            @RequestBody ProfessorPatchDTO patchDto) {

        ProfessorDTO ProfessorAtualizado = professorService.atualizarParcial(id, patchDto);

        return ResponseEntity.ok(ProfessorAtualizado);
    }
}