package com.escola.diario_escolar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.escola.diario_escolar.dto.boletim.BoletimAlunoDto;
import com.escola.diario_escolar.service.BoletimService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Boletins", description = "Geração de boletim escolar dos alunos")
@RestController
@RequestMapping("/api/boletins")
public class BoletimController {

    private final BoletimService boletimService;

    public BoletimController(BoletimService boletimService) {
        this.boletimService = boletimService;
    }

    @Operation(summary = "Gerar boletim do aluno", 
            description = "Gera e retorna o boletim completo de um aluno contendo suas disciplinas, notas e médias.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Boletim gerado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado"),
            @ApiResponse(responseCode = "400", description = "ID do aluno inválido")
    })
    @GetMapping("/aluno/{alunoId}")
    public ResponseEntity<BoletimAlunoDto> gerar(
            @Parameter(description = "ID do aluno para geração do boletim", example = "1") @PathVariable Long alunoId) {
        return ResponseEntity.created(null).body(boletimService.gerarBoletim(alunoId));

    }
}