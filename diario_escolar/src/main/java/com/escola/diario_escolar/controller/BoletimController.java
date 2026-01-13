package com.escola.diario_escolar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.escola.diario_escolar.dto.boletim.BoletimAlunoDto;
import com.escola.diario_escolar.service.BoletimService;

@RestController
@RequestMapping("/boletins")
public class BoletimController {

    private final BoletimService boletimService;

    public BoletimController(BoletimService boletimService) {
        this.boletimService = boletimService;
    }

    @GetMapping("/aluno/{alunoId}")
    public ResponseEntity<BoletimAlunoDto> gerar(
        @PathVariable Long alunoId
    ) {
        return ResponseEntity.ok(
            boletimService.gerarBoletim(alunoId)
        );
    }
}


