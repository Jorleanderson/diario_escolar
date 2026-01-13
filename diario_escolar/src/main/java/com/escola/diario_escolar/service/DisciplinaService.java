package com.escola.diario_escolar.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.escola.diario_escolar.dto.disciplina.DisciplinaDto;
import com.escola.diario_escolar.dto.disciplina.DisciplinaPatchDto;
import com.escola.diario_escolar.dto.disciplina.DisciplinaResponseDto;
import com.escola.diario_escolar.exception.ApiException;
import com.escola.diario_escolar.mapper.DisciplinaMapper;
import com.escola.diario_escolar.model.Disciplina;
import com.escola.diario_escolar.model.Professor;
import com.escola.diario_escolar.model.Turma;
import com.escola.diario_escolar.repository.DisciplinaRepository;

@Service
public class DisciplinaService extends BaseService<Disciplina, Long> {

    private final DisciplinaRepository repository;
    private final TurmaService turmaService;
    private final ProfessorService professorService;

    public DisciplinaService(DisciplinaRepository repository, TurmaService turmaService,
            ProfessorService professorService) {
        super(repository, "Disciplina");
        this.repository = repository;
        this.turmaService = turmaService;
        this.professorService = professorService;
    }

    public DisciplinaResponseDto criarDisciplina(DisciplinaDto dto) {

        boolean jaExiste = repository.existsByNomeAndTurmaId(
                dto.getNome(),
                dto.getTurmaId());

        if (jaExiste) {
            throw new ApiException(
                    "Já existe uma disciplina com esse nome nesta turma.",
                    HttpStatus.CONFLICT);
        }

        Turma turma = turmaService.findEntityById(dto.getTurmaId());
        Professor professor = professorService.findEntityById(dto.getProfessorId());

        Disciplina disciplina = DisciplinaMapper.toEntity(dto, turma, professor);

        return DisciplinaMapper.toResponseDto(
                repository.save(disciplina));
    }

    public List<DisciplinaResponseDto> listarTodos() {
        return repository.findAll()
                .stream()
                .map(DisciplinaMapper::toResponseDto)
                .toList();
    }

    public DisciplinaResponseDto buscarPorId(Long id) {
        return DisciplinaMapper.toResponseDto(findEntityById(id));
    }

    public List<Disciplina> listarPorTurma(Long turmaId) {
        return repository.findByTurmaId(turmaId);
    }

    public DisciplinaResponseDto atualizar(Long id, DisciplinaDto atualizado) {
        Disciplina disciplina = findEntityById(id);

        disciplina.setNome(atualizado.getNome());
        disciplina.setCargaHoraria(atualizado.getCargaHoraria());

        return DisciplinaMapper.toResponseDto(repository.save(disciplina));
    }

    public void deletar(Long id) {
        Disciplina disciplina = findEntityById(id);
        repository.delete(disciplina);
    }

    public DisciplinaDto atualizarParcial(Long id, DisciplinaPatchDto patchDto) {

        Disciplina disciplina = findEntityById(id);

        if (patchDto.getNome() != null && !patchDto.getNome().isBlank()) {
            disciplina.setNome(patchDto.getNome());
        }

        if (patchDto.getCargaHoraria() != null) {
            disciplina.setCargaHoraria(patchDto.getCargaHoraria());
        }

        Disciplina atualizado = repository.save(disciplina);

        return DisciplinaMapper.toDto(atualizado);
    }

    public Page<DisciplinaDto> listarPaginado(Pageable pageable) {
        return repository.findAll(pageable)
                .map(DisciplinaMapper::toDto);
    }
}