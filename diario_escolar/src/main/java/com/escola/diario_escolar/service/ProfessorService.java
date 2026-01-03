package com.escola.diario_escolar.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.escola.diario_escolar.dto.ProfessorDto;
import com.escola.diario_escolar.dto.ProfessorPatchDto;
import com.escola.diario_escolar.exception.ApiException;
import com.escola.diario_escolar.mapper.ProfessorMapper;
import com.escola.diario_escolar.model.Professor;
import com.escola.diario_escolar.repository.ProfessorRepository;

@Service
public class ProfessorService extends BaseService<Professor,UUID>{

    private final ProfessorRepository repository;

    public ProfessorService(ProfessorRepository repository) {
        super(repository, "Professor");
        this.repository = repository;
    }
    
    public ProfessorDto criarProfessor(ProfessorDto dto) {

        if (repository.existsByEmail(dto.getEmail())) {
            throw new ApiException(
                 "Já existe um professor cadastrado com este e-mail",
                HttpStatus.CONFLICT
            );
        }
        Professor professor = ProfessorMapper.toEntity(dto);
    
        return ProfessorMapper.toDTO(repository.save(professor));
    }
    
    public List<ProfessorDto> listarTodos() {
        return repository.findAll()
                .stream()
                .map(ProfessorMapper::toDTO)
                .toList();
    }

    public ProfessorDto buscarPorId(UUID id) {
        return ProfessorMapper.toDTO(findEntityById(id));
    }

    public ProfessorDto atualizar(UUID id, ProfessorDto atualizado) {
        Professor professor = findEntityById(id);

        professor.setNome(atualizado.getNome());
        professor.setEmail(atualizado.getEmail());
        professor.setDisciplina(atualizado.getDisciplina());
        professor.setFormacao(atualizado.getFormacao());

        return ProfessorMapper.toDTO(repository.save(professor));
    }

    public void deletar(UUID id) {
        Professor professor = findEntityById(id);
        repository.delete(professor);
    }
    
    public ProfessorDto atualizarParcial(UUID id, ProfessorPatchDto patchDto) {

        Professor professor = findEntityById(id);

        if (patchDto.getNome() != null && !patchDto.getNome().isBlank()){
            professor.setNome(patchDto.getNome());
        }

        if (patchDto.getEmail() != null && !patchDto.getEmail().isBlank()) {
            professor.setEmail(patchDto.getEmail());
        }
 
        if (patchDto.getDisciplina() != null && !patchDto.getDisciplina().isBlank()) {
            professor.setDisciplina(patchDto.getDisciplina());
        }

        if (patchDto.getFormacao() != null && !patchDto.getFormacao().isBlank()) {
            professor.setFormacao(patchDto.getFormacao());
        }

        Professor atualizado = repository.save(professor);

        return ProfessorMapper.toDTO(atualizado);
    }
    
    public Page<ProfessorDto> listarPaginado(Pageable pageable) {

        return repository.findAll(pageable)
                .map(ProfessorMapper::toDTO);
    }
}

