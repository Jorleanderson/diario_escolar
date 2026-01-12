package com.escola.diario_escolar.dto.disciplina;

import java.math.BigDecimal;
import java.util.List;

import com.escola.diario_escolar.dto.aluno.AlunoResumoDto;
import com.escola.diario_escolar.dto.nota.NotaResumoDto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BoletimDisciplinaDto {

    private AlunoResumoDto aluno;
    private DisciplinaResumoDto disciplina;
    private List<NotaResumoDto> notas;
    private BigDecimal media;
    private String situacao; 
}

