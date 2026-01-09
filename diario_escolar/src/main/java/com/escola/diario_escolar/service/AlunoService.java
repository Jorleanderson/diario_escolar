package com.escola.diario_escolar.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.escola.diario_escolar.dto.aluno.AlunoDto;
import com.escola.diario_escolar.dto.aluno.AlunoPatchDto;
import com.escola.diario_escolar.dto.aluno.AlunoResponseDto;
import com.escola.diario_escolar.exception.ApiException;
import com.escola.diario_escolar.mapper.AlunoMapper;
import com.escola.diario_escolar.model.AlunoEntity;
import com.escola.diario_escolar.model.Turma;
import com.escola.diario_escolar.repository.AlunoRepository;

@Service
public class AlunoService extends BaseService<AlunoEntity, Long> {

    private final AlunoRepository alunoRepository;
    private final TurmaService turmaService;

    public AlunoService(AlunoRepository repository, TurmaService turmaService) {
        super(repository, "Aluno");
        this.alunoRepository = repository;
        this.turmaService = turmaService;
    }

    public AlunoResponseDto buscarPorId(Long id) {

        return AlunoMapper.toResponseDto(findEntityById(id));
    }

    public AlunoResponseDto criarAluno(AlunoDto alunoDto) {

        if (alunoRepository.existsByEmail(alunoDto.getEmail())) {
            throw new ApiException(
                    "Já existe um aluno cadastrado com este e-mail",
                    HttpStatus.CONFLICT);
        }

        if (alunoRepository.existsByMatricula(alunoDto.getMatricula())) {
            throw new ApiException(
                    "Já existe um aluno cadastrado com esta matrícula",
                    HttpStatus.CONFLICT);
        }

        Turma turma = turmaService.findEntityById(alunoDto.getTurmaId());
        AlunoEntity aluno = AlunoMapper.toEntity(alunoDto, turma);

        AlunoEntity alunoSalvo = alunoRepository.save(aluno);

        return AlunoMapper.toResponseDto(alunoSalvo);
    }

    public Page<AlunoResponseDto> listarPaginado(Pageable pageable) {
        return repository.findAll(pageable)
                .map(AlunoMapper::toResponseDto);
    }

    public List<AlunoResponseDto> listarTodos() {
        return repository.findAll()
                .stream()
                .map(AlunoMapper::toResponseDto)
                .toList();
    }

    public AlunoResponseDto atualizar(Long id, AlunoDto novoAluno) {

        AlunoEntity aluno = findEntityById(id);

        aluno.setDataNascimento(novoAluno.getDataNascimento());
        aluno.setEmail(novoAluno.getEmail());
        aluno.setMatricula(novoAluno.getMatricula());
        aluno.setNome(novoAluno.getNome());

        return AlunoMapper.toResponseDto(repository.save(aluno));
    }

    public void deletar(Long id) {

        repository.deleteById(id);
    }

    public AlunoResponseDto atualizarParcial(Long id, AlunoPatchDto alunoPatch) {

        AlunoEntity aluno = findEntityById(id);

        if (alunoPatch.getNome() != null && !alunoPatch.getNome().isBlank()) {
            aluno.setNome(alunoPatch.getNome());
        }

        if (alunoPatch.getEmail() != null && !alunoPatch.getEmail().isBlank()) {
            aluno.setEmail(alunoPatch.getEmail());
        }

        if (alunoPatch.getMatricula() != null && !alunoPatch.getMatricula().isBlank()) {
            aluno.setMatricula(alunoPatch.getMatricula());
        }

        if (alunoPatch.getDataNascimento() != null) {
            aluno.setDataNascimento(alunoPatch.getDataNascimento());
        }

        AlunoEntity alunoAtualizado = repository.save(aluno);

        return AlunoMapper.toResponseDto(alunoAtualizado);
    }

    public List<AlunoResponseDto> listarPorTurma(Long turmaId) {

        turmaService.findEntityById(turmaId);

        List<AlunoEntity> alunos = alunoRepository.findByTurmaId(turmaId);

        return alunos.stream()
                .map(AlunoMapper::toResponseDto)
                .toList();
    }

    public Page<AlunoResponseDto> listarPorTurmaPaginado(Long turmaId, Pageable pageable) {

        turmaService.findEntityById(turmaId);

        return alunoRepository
                .findByTurmaId(turmaId, pageable)
                .map(AlunoMapper::toResponseDto);
    }

}