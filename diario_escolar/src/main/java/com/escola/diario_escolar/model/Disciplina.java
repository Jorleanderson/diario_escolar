package com.escola.diario_escolar.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "disciplinas")
public class Disciplina {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(nullable = false, length = 100)
private String nome;

@Column(name = "carga_horaria", nullable = false)
private Integer cargaHoraria;

public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public String getNome() {
    return nome;
}

public void setNome(String nome) {
    this.nome = nome;
}

public Integer getCargaHoraria() {
    return cargaHoraria;
}

public void setCargaHoraria(Integer cargaHoraria) {
    this.cargaHoraria = cargaHoraria;
}
}
