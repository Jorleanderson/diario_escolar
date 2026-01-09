package com.escola.diario_escolar.dto.aluno;

import java.time.LocalDate;

import com.escola.diario_escolar.dto.turma.TurmaResumoDto;

public class AlunoResponseDto {

    private Long id;
    private String nome;
    private String matricula;
    private LocalDate dataNascimento;
    private String email;
    private TurmaResumoDto turma;
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
    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public TurmaResumoDto getTurma() {
        return turma;
    }
    public void setTurma(TurmaResumoDto turma) {
        this.turma = turma;
    }

  
}

