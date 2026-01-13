package com.escola.diario_escolar.dto.boletim;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NotaBoletimDto {
    private Integer trimestre;
    private BigDecimal valor;
}
