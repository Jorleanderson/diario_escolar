package com.escola.diario_escolar.dto;

import java.math.BigDecimal;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

public class NotaDto {

	private Long id;

	@NotNull(message = "O trimestre é obrigatório")
	private Integer trimestre;

	@NotNull(message = "O valor da nota é obrigatório")
	@DecimalMin(value = "0.0", inclusive = true, message = "O valor da nota deve ser positivo")
	@Digits(integer = 8, fraction = 2, message = "Formato de nota inválido")
	private BigDecimal valor;

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
