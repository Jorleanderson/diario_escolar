package com.escola.diario_escolar.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.escola.diario_escolar.dto.DisciplinaDto;
import com.escola.diario_escolar.dto.DisciplinaPatchDto;
import com.escola.diario_escolar.mapper.DisciplinaMapper;
import com.escola.diario_escolar.model.Disciplina;
import com.escola.diario_escolar.repository.DisciplinaRepository;

@Service
public class DisciplinaService extends BaseService<Disciplina, Long> {

    private final DisciplinaRepository repository;

    public DisciplinaService(DisciplinaRepository repository) {
        super(repository, "Disciplina");
        this.repository = repository;
    }

    public DisciplinaDto criarDisciplina(DisciplinaDto dto) {
        Disciplina disciplina = DisciplinaMapper.toEntity(dto);

        return DisciplinaMapper.toDto(repository.save(disciplina));
    }

    public List<DisciplinaDto> listarTodos() {
        return repository.findAll()
                .stream()
                .map(DisciplinaMapper::toDto)
                .toList();
    }

    public DisciplinaDto buscarPorId(Long id) {
        return DisciplinaMapper.toDto(findEntityById(id));
    }

    public DisciplinaDto atualizar(Long id, DisciplinaDto atualizado) {
        Disciplina disciplina = findEntityById(id);

        disciplina.setNome(atualizado.getNome());
        disciplina.setCargaHoraria(atualizado.getCargaHoraria());

        return DisciplinaMapper.toDto(repository.save(disciplina));
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