package com.escola.diario_escolar.model;

import java.math.BigDecimal;

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
@Table(name = "notas",
       uniqueConstraints = {
           @UniqueConstraint(
               columnNames = {"aluno_id", "disciplina_id", "trimestre"}
           )
       })
       
public class Nota {
    
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(nullable = false)
private Integer trimestre;

@Column(nullable = false, precision = 10, scale = 2)
private BigDecimal valor;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "aluno_id", nullable = false)
private AlunoEntity aluno;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "disciplina_id", nullable = false)
private Disciplina disciplina;

public AlunoEntity getAluno() {
    return aluno;
}

public void setAluno(AlunoEntity aluno) {
    this.aluno = aluno;
}

public Disciplina getDisciplina() {
    return disciplina;
}

public void setDisciplina(Disciplina disciplina) {
    this.disciplina = disciplina;
}

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