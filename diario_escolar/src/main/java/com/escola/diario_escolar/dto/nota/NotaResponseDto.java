package com.escola.diario_escolar.dto.nota;

import java.math.BigDecimal;

import com.escola.diario_escolar.dto.aluno.AlunoResumoDto;
import com.escola.diario_escolar.dto.disciplina.DisciplinaResumoDto;

public class NotaResponseDto {

    private Long id;
    private Integer trimestre;
    private BigDecimal valor;
    private AlunoResumoDto aluno;
    private DisciplinaResumoDto disciplina;

    
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
    public AlunoResumoDto getAluno() {
        return aluno;
    }
    public void setAluno(AlunoResumoDto aluno) {
        this.aluno = aluno;
    }
    public DisciplinaResumoDto getDisciplina() {
        return disciplina;
    }
    public void setDisciplina(DisciplinaResumoDto disciplina) {
        this.disciplina = disciplina;
    }

}
