package com.escola.diario_escolar.dto.professor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ProfessorDto {
	
	
	private Long id; 
	@NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 75, message = "O nome deve ter entre 3 e 100 caracteres")
	private String nome;
	@NotBlank(message = "O e-mail do professor é obrigatório.")
	@Email(message = "Informe um e-mail válido.")
    private String email;
	@NotBlank(message = "A disciplina do professor é obrigatória.")
    private String disciplina;
	@NotBlank(message = "A formação do professor é obrigatória.")
    private String formacao;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getFormacao() {
		return formacao;
	}
	public void setFormacao(String formacao) {
		this.formacao = formacao;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDisciplina() {
		return disciplina;
	}
	
	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

}
