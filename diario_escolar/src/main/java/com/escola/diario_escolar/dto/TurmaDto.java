package com.escola.diario_escolar.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TurmaDto {


    private Long id;

    @NotBlank( message = "O código é obrigatório")
    private String codigo;

    @NotBlank( message = "O nome é obrigatório")
    private String nome;

    @NotNull( message = "O ano letivo é obrigatório")
    private Integer anoLetivo;

    @NotBlank( message = "O turno é obrigatório")
    private String turno;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAnoLetivo() {
        return anoLetivo;
    }

    public void setAnoLetivo(Integer anoLetivo) {
        this.anoLetivo = anoLetivo;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }
}
