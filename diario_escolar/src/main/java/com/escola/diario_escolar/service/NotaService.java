package com.escola.diario_escolar.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.escola.diario_escolar.dto.disciplina.BoletimDisciplinaDto;
import com.escola.diario_escolar.dto.nota.NotaDto;
import com.escola.diario_escolar.dto.nota.NotaPatchDto;
import com.escola.diario_escolar.dto.nota.NotaResponseDto;
import com.escola.diario_escolar.dto.nota.NotaResumoDto;
import com.escola.diario_escolar.enums.SituacaoAluno;
import com.escola.diario_escolar.exception.ApiException;
import com.escola.diario_escolar.mapper.AlunoMapper;
import com.escola.diario_escolar.mapper.DisciplinaMapper;
import com.escola.diario_escolar.mapper.NotaMapper;
import com.escola.diario_escolar.model.AlunoEntity;
import com.escola.diario_escolar.model.Disciplina;
import com.escola.diario_escolar.model.Nota;
import com.escola.diario_escolar.repository.NotaRepository;

@Service
public class NotaService extends BaseService<Nota, Long> {

	private final NotaRepository notaRepository;
	private final AlunoService alunoService;
	private final DisciplinaService disciplinaService;

	public NotaService(NotaRepository notaRepository,
			AlunoService alunoService,
			DisciplinaService disciplinaService) {
		super(notaRepository, "Nota");
		this.notaRepository = notaRepository;
		this.alunoService = alunoService;
		this.disciplinaService = disciplinaService;
	}

	public NotaResponseDto criarNota(NotaDto dto) {

		AlunoEntity aluno = alunoService.findEntityById(dto.getAlunoId());
		Disciplina disciplina = disciplinaService.findEntityById(dto.getDisciplinaId());

		if (notaRepository.existsByAlunoIdAndDisciplinaIdAndTrimestre(
				aluno.getId(),
				disciplina.getId(),
				dto.getTrimestre())) {

			throw new ApiException(
					"Já existe nota lançada para este aluno nesta disciplina e trimestre",
					HttpStatus.CONFLICT);
		}

		Nota nota = NotaMapper.toEntity(dto, aluno, disciplina);

		return NotaMapper.toResponseDto(
				notaRepository.save(nota));
	}

	public List<NotaResumoDto> listarTodos() {
		return notaRepository.findAll()
				.stream()
				.map(NotaMapper::toResumo)
				.toList();
	}

	public NotaDto buscarPorId(Long id) {
		return NotaMapper.toDto(findEntityById(id));
	}

	public NotaDto atualizar(Long id, NotaDto atualizado) {
		Nota nota = findEntityById(id);

		nota.setTrimestre(atualizado.getTrimestre());
		nota.setValor(atualizado.getValor());

		return NotaMapper.toDto(notaRepository.save(nota));
	}

	public void deletar(Long id) {
		Nota nota = findEntityById(id);
		notaRepository.delete(nota);
	}

	public NotaDto atualizarParcial(Long id, NotaPatchDto patchDto) {

		Nota nota = findEntityById(id);

		if (patchDto.getTrimestre() != null) {
			nota.setTrimestre(patchDto.getTrimestre());
		}

		if (patchDto.getValor() != null) {
			nota.setValor(patchDto.getValor());
		}

		Nota atualizado = notaRepository.save(nota);

		return NotaMapper.toDto(atualizado);
	}

	public Page<NotaDto> listarPaginado(Pageable pageable) {
		return notaRepository.findAll(pageable)
				.map(NotaMapper::toDto);
	}

	private BigDecimal calcularMedia(List<Nota> notas) {

		BigDecimal soma = notas.stream()
				.map(Nota::getValor)
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		return soma.divide(
				BigDecimal.valueOf(notas.size()),
				2,
				RoundingMode.HALF_UP);
	}

	private String definirSituacao(
			List<Nota> notas,
			BigDecimal media) {

		if (notas.size() < 4) {
			return SituacaoAluno.EM_ANDAMENTO.name();
		}

		if (media.compareTo(BigDecimal.valueOf(7.0)) >= 0) {
			return SituacaoAluno.APROVADO.name();
		}

		return SituacaoAluno.REPROVADO.name();
	}

	public BoletimDisciplinaDto gerarBoletim(Long alunoId, Long disciplinaId) {

		AlunoEntity aluno = alunoService.findEntityById(alunoId);
		Disciplina disciplina = disciplinaService.findEntityById(disciplinaId);

		List<Nota> notas = notaRepository.findByAlunoIdAndDisciplinaId(alunoId, disciplinaId);

		if (notas.isEmpty()) {
			throw new ApiException(
					"Nenhuma nota encontrada para este aluno nesta disciplina",
					HttpStatus.NOT_FOUND);
		}

		BigDecimal media = calcularMedia(notas);
		String situacao = definirSituacao(notas, media);

		BoletimDisciplinaDto boletim = new BoletimDisciplinaDto();
		boletim.setAluno(AlunoMapper.toResumo(aluno));
		boletim.setDisciplina(DisciplinaMapper.toResumo(disciplina));
		boletim.setNotas(
				notas.stream()
						.map(NotaMapper::toResumo)
						.toList());
		boletim.setMedia(media);
		boletim.setSituacao(situacao);

		return boletim;
	}

}
