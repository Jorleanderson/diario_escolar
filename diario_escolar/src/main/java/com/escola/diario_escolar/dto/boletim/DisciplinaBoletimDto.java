package com.escola.diario_escolar.dto.boletim;

import java.math.BigDecimal;
import java.util.List;

import com.escola.diario_escolar.enums.SituacaoAluno;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DisciplinaBoletimDto {
    private String nome;
    private List<NotaBoletimDto> notas;
    private BigDecimal media;
    private SituacaoAluno situacao;
}
