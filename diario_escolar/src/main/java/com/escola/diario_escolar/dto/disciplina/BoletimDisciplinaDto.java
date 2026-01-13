package com.escola.diario_escolar.dto.disciplina;

import java.math.BigDecimal;
import java.util.List;

import com.escola.diario_escolar.dto.nota.NotaResumoDto;




public class BoletimDisciplinaDto {

	
    private DisciplinaResumoDto disciplina;
    private List<NotaResumoDto> notas;
    private BigDecimal media;
    private String situacao;


	public DisciplinaResumoDto getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(DisciplinaResumoDto disciplina) {
		this.disciplina = disciplina;
	}
	public List<NotaResumoDto> getNotas() {
		return notas;
	}
	public void setNotas(List<NotaResumoDto> notas) {
		this.notas = notas;
	}
	public BigDecimal getMedia() {
		return media;
	}
	public void setMedia(BigDecimal media) {
		this.media = media;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	 
}

