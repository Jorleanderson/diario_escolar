package com.escola.diario_escolar.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    name = "disciplinas",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"nome", "turma_id"})
    }
)
public class Disciplina {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
@Column(nullable = false, length = 100)
private String nome;
@Column(name = "carga_horaria", nullable = false)
private Integer cargaHoraria;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "turma_id", nullable = false)
private Turma turma;
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "professor_id", nullable = false)
private Professor professor;


public Turma getTurma() {
    return turma;
}

public void setTurma(Turma turma) {
    this.turma = turma;
}

public Professor getProfessor() {
    return professor;
}

public void setProfessor(Professor professor) {
    this.professor = professor;
}

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
