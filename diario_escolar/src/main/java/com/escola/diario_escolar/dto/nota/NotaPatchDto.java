package com.escola.diario_escolar.dto.nota;

import java.math.BigDecimal;

public class NotaPatchDto {

    private Integer trimestre;
    private BigDecimal valor;

    public Integer getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(Integer trimestre) {
        this.trimestre = trimestre;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

}
