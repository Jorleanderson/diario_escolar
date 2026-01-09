package com.escola.diario_escolar.dto.turma;

public class TurmaPatchDto {
    private String codigo;
    private String nome;
    private Integer anoLetivo;
    private String turno;
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
