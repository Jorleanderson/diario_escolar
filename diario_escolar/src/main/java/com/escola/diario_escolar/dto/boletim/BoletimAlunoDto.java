package com.escola.diario_escolar.dto.boletim;

import java.util.List;

import com.escola.diario_escolar.dto.aluno.AlunoResumoDto;
import com.escola.diario_escolar.dto.disciplina.BoletimDisciplinaDto;
import com.escola.diario_escolar.dto.turma.TurmaResumoDto;

public class BoletimAlunoDto {


    private AlunoResumoDto aluno;
	private TurmaResumoDto turma;
    private List<BoletimDisciplinaDto> disciplinas;
	
    public AlunoResumoDto getAluno() {
		return aluno;
	}
	public void setAluno(AlunoResumoDto aluno) {
		this.aluno = aluno;
	}
	public TurmaResumoDto getTurma() {
		return turma;
	}
	public void setTurma(TurmaResumoDto turma) {
		this.turma = turma;
	}
	public List<BoletimDisciplinaDto> getDisciplinas() {
		return disciplinas;
	}
	public void setDisciplinas(List<BoletimDisciplinaDto> disciplinas) {
		this.disciplinas = disciplinas;
	}
	
}
