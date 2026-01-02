package com.escola.diario_escolar.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.escola.diario_escolar.dto.ProfessorDTO;
import com.escola.diario_escolar.dto.ProfessorPatchDTO;
import com.escola.diario_escolar.exception.ApiException;
import com.escola.diario_escolar.mapper.ProfessorMapper;
import com.escola.diario_escolar.model.Professor;
import com.escola.diario_escolar.repository.ProfessorRepository;

@Service
public class ProfessorService {

    private final ProfessorRepository repository;

    public ProfessorService(ProfessorRepository repository) {
        this.repository = repository;
    }
    
    public ProfessorDTO criarProfessor(ProfessorDTO dto) {

        if (repository.existsByEmail(dto.getEmail())) {
            throw new ApiException(
                 "Já existe um professor cadastrado com este e-mail",
                HttpStatus.CONFLICT
            );
        }
        Professor professor = ProfessorMapper.toEntity(dto);
        Professor salvo = repository.save(professor);
        return ProfessorMapper.toDTO(salvo);
    }
    
    public List<ProfessorDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(ProfessorMapper::toDTO)
                .toList();
    }

    private Professor findEntityById(UUID id) {
        return repository.findById(id)
        		.orElseThrow(() -> new ApiException("Professor não encontrado.", HttpStatus.NOT_FOUND));
    }

    public ProfessorDTO buscarPorId(UUID id) {
        return ProfessorMapper.toDTO(findEntityById(id));
    }

    public ProfessorDTO atualizar(UUID id, ProfessorDTO novoProfessor) {
        Professor professor = findEntityById(id);

        professor.setNome(novoProfessor.getNome());
        professor.setEmail(novoProfessor.getEmail());
        professor.setDisciplina(novoProfessor.getDisciplina());
        professor.setFormacao(novoProfessor.getFormacao());

        return ProfessorMapper.toDTO(repository.save(professor));
    }

    public void deletar(UUID id) {
        Professor professor = findEntityById(id);
        repository.delete(professor);
    }
    
    public ProfessorDTO atualizarParcial(UUID id, ProfessorPatchDTO patchDto) {

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
    
    public Page<ProfessorDTO> listarPaginado(Pageable pageable) {

        return repository.findAll(pageable)
                .map(ProfessorMapper::toDTO);
    }
}

