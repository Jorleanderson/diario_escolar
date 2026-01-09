package com.escola.diario_escolar.dto.disciplina;

import com.escola.diario_escolar.dto.professor.ProfessorResumoDto;
import com.escola.diario_escolar.dto.turma.TurmaResumoDto;

public class DisciplinaResponseDto {

    private Long id;
    private String nome;
    private Integer cargaHoraria;
    private TurmaResumoDto turma;
    private ProfessorResumoDto professor;
    
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
    public TurmaResumoDto getTurma() {
        return turma;
    }
    public void setTurma(TurmaResumoDto turma) {
        this.turma = turma;
    }
    public ProfessorResumoDto getProfessor() {
        return professor;
    }
    public void setProfessor(ProfessorResumoDto professor) {
        this.professor = professor;
    }
}
