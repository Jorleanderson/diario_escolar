package com.escola.diario_escolar.dto.nota;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class NotaDto {

	private Long id;

	@NotNull(message = "O trimestre é obrigatório")
	@Min(value = 1, message = "O trimestre deve ser entre 1 e 4")
	@Max(value = 4, message = "O trimestre deve ser entre 1 e 4")
	private Integer trimestre;

	@NotNull(message = "O valor da nota é obrigatório")
	@DecimalMin(value = "0.0", inclusive = true, message = "O valor da nota deve ser positivo")
	@DecimalMax(value = "10.0", inclusive = true, message = "O valor da nota não pode ser maior que 10.0")
	@Digits(integer = 8, fraction = 2, message = "Formato de nota inválido")
	private BigDecimal valor;

	@NotNull(message = "O ID do aluno é obrigatório")
	private Long alunoId;

	@NotNull(message = "O ID da disciplina é obrigatório")
	private Long disciplinaId;

	public Long getAlunoId() {
		return alunoId;
	}

	public void setAlunoId(Long alunoId) {
		this.alunoId = alunoId;
	}

	public Long getDisciplinaId() {
		return disciplinaId;
	}

	public void setDisciplinaId(Long disciplinaId) {
		this.disciplinaId = disciplinaId;
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
