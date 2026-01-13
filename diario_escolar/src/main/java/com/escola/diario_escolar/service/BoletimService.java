package com.escola.diario_escolar.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.stereotype.Service;

import com.escola.diario_escolar.dto.boletim.BoletimAlunoDto;
import com.escola.diario_escolar.dto.disciplina.BoletimDisciplinaDto;
import com.escola.diario_escolar.enums.SituacaoAluno;
import com.escola.diario_escolar.mapper.AlunoMapper;
import com.escola.diario_escolar.mapper.DisciplinaMapper;
import com.escola.diario_escolar.mapper.NotaMapper;
import com.escola.diario_escolar.mapper.TurmaMapper;
import com.escola.diario_escolar.model.AlunoEntity;
import com.escola.diario_escolar.model.Disciplina;
import com.escola.diario_escolar.model.Nota;
import com.escola.diario_escolar.model.Turma;
import com.escola.diario_escolar.repository.NotaRepository;

@Service
public class BoletimService {

    private final AlunoService alunoService;
    private final DisciplinaService disciplinaService;
    private final NotaRepository notaRepository;

    public BoletimService(
        AlunoService alunoService,
        DisciplinaService disciplinaService,
        NotaRepository notaRepository
    ) {
        this.alunoService = alunoService;
        this.disciplinaService = disciplinaService;
        this.notaRepository = notaRepository;
    }

    public BoletimAlunoDto gerarBoletim(Long alunoId) {

        AlunoEntity aluno = alunoService.findEntityById(alunoId);
        Turma turma = aluno.getTurma();

        List<Disciplina> disciplinas =
            disciplinaService.listarPorTurma(turma.getId());

        List<BoletimDisciplinaDto> boletimDisciplinas = disciplinas.stream()
                .map(disciplina -> montarBoletimDisciplina(aluno, disciplina))
                .toList();

        BoletimAlunoDto boletim = new BoletimAlunoDto();
        boletim.setAluno(AlunoMapper.toResumo(aluno));
        boletim.setTurma(TurmaMapper.toResumo(turma));
        boletim.setDisciplinas(boletimDisciplinas);

        return boletim;
    }

    private BigDecimal calcularMedia(List<Nota> notas) {

		BigDecimal soma = notas.stream()
				.map(Nota::getValor)
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		return soma.divide(
				BigDecimal.valueOf(4),
				2,
				RoundingMode.HALF_UP);
	}

	
    private String calcularSituacao(
        List<Nota> notas,
			BigDecimal media
    ) {

        if (notas.size() < 4) {
            return SituacaoAluno.EM_ANDAMENTO.name();
        }

        return media.compareTo(BigDecimal.valueOf(7)) >= 0
            ? SituacaoAluno.APROVADO.name()
            : SituacaoAluno.REPROVADO.name();
    }

	private BoletimDisciplinaDto montarBoletimDisciplina(
        AlunoEntity aluno,
        Disciplina disciplina) {

    List<Nota> notas =
        notaRepository.findByAlunoIdAndDisciplinaId(
            aluno.getId(),
            disciplina.getId()
        );

    BoletimDisciplinaDto boletim = new BoletimDisciplinaDto();
    boletim.setDisciplina(DisciplinaMapper.toResumo(disciplina));

   if (notas.isEmpty()) {
        boletim.setNotas(List.of()); // lista vazia
        boletim.setMedia(BigDecimal.valueOf(0));
        boletim.setSituacao(SituacaoAluno.SEM_NOTAS.name());
        return boletim; 
    }

    BigDecimal media = calcularMedia(notas);
    String situacao = calcularSituacao(notas, media);

    

    boletim.setDisciplina(DisciplinaMapper.toResumo(disciplina));
    boletim.setNotas(
        notas.stream()
        .map(NotaMapper::toResumo)
        .toList()
    );
    boletim.setMedia(media);
    boletim.setSituacao(situacao);

    return boletim;
}

}

