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

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/turmas")

public class TurmaController {
    private TurmaService turmaService;
    private AlunoService alunoService;

    public TurmaController(TurmaService turmaService, AlunoService alunoService) {
        this.turmaService = turmaService;
        this.alunoService = alunoService;
    }

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

    @GetMapping()
    public ResponseEntity<List<TurmaDto>> listarTodos() {

        List<TurmaDto> turmas = turmaService.listarTodos();

        return ResponseEntity.ok(turmas);
    }

    @GetMapping("/paginado")
    public ResponseEntity<Page<TurmaDto>> listarPaginado(
            @PageableDefault(size = 10, sort = "nome") Pageable pageable) {

        Page<TurmaDto> turmas = turmaService.listarPaginado(pageable);

        return ResponseEntity.ok(turmas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurmaDto> buscarPorId(@PathVariable("id") Long id) {
        TurmaDto turma = turmaService.buscarPorId(id);
        return ResponseEntity.ok(turma);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TurmaDto> atualizar(
            @PathVariable("id") Long id,
            @RequestBody @Valid TurmaDto dto) {

        TurmaDto turmaAtualizada = turmaService.atualizar(dto, id);

        return ResponseEntity.ok(turmaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        turmaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TurmaDto> atualizarParcial(
            @PathVariable("id") Long id,
            @RequestBody TurmaPatchDto patchDto) {

        TurmaDto turmaAtualizada = turmaService.atualizarParcial(id, patchDto);

        return ResponseEntity.ok(turmaAtualizada);
    }

    @GetMapping("/{turmaId}/alunos")
    public ResponseEntity<List<AlunoResponseDto>> listarAlunosDaTurma(
            @PathVariable Long turmaId) {

        return ResponseEntity.ok(alunoService.listarPorTurma(turmaId));
    }

    @GetMapping("/{turmaId}/alunos/paginado")
    public ResponseEntity<Page<AlunoResponseDto>> listarAlunosDaTurmaPaginado(
            @PathVariable Long turmaId,
            @PageableDefault(size = 5) Pageable pageable) {

        return ResponseEntity.ok(
                alunoService.listarPorTurmaPaginado(turmaId, pageable));
    }

}