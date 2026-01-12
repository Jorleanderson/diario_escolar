package com.escola.diario_escolar.dto.nota;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NotaResumoDto {

    private Long id;
    private Integer trimestre;
    private BigDecimal valor;
}

