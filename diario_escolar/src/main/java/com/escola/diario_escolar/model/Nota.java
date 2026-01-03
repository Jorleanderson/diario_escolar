package com.escola.diario_escolar.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "notas")
public class Nota {
    
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(nullable = false)
private Integer trimestre;

@Column(nullable = false, precision = 10, scale = 2)
private BigDecimal valor;

public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

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