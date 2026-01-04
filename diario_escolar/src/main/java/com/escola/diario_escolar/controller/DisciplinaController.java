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

import com.escola.diario_escolar.dto.DisciplinaDto;
import com.escola.diario_escolar.dto.DisciplinaPatchDto;
import com.escola.diario_escolar.service.DisciplinaService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/disciplinas")
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

    public DisciplinaController(DisciplinaService service) {
        this.disciplinaService = service;
    }

    @PostMapping
    public ResponseEntity<DisciplinaDto> postDisciplina(
            @RequestBody @Valid DisciplinaDto dto) {
        DisciplinaDto criado = disciplinaService.criarDisciplina(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(criado.getId())
                .toUri();

        return ResponseEntity.created(location).body(criado);
    }

    @GetMapping("/paginado")
    public ResponseEntity<Page<DisciplinaDto>> listarPaginado(
            @PageableDefault(size = 10, sort = "nome") Pageable pageable) {

        Page<DisciplinaDto> pagina = disciplinaService.listarPaginado(pageable);

        return ResponseEntity.ok(pagina);

    }

    @GetMapping
    public ResponseEntity<List<DisciplinaDto>> listarTodos() {
        List<DisciplinaDto> list = disciplinaService.listarTodos();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisciplinaDto> buscarPorId(
            @PathVariable("id") Long id) {

        DisciplinaDto dto = disciplinaService.buscarPorId(id);

        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisciplinaDto> atualizar(
            @PathVariable("id") Long id,
            @RequestBody @Valid DisciplinaDto disciplinaDto) {

        DisciplinaDto atualizado = disciplinaService.atualizar(id, disciplinaDto);

        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        disciplinaService.deletar(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DisciplinaDto> atualizarParcial(
            @PathVariable("id") Long id,
            @RequestBody DisciplinaPatchDto patchDto) {

        DisciplinaDto atualizado = disciplinaService.atualizarParcial(id, patchDto);

        return ResponseEntity.ok(atualizado);
    }

}
