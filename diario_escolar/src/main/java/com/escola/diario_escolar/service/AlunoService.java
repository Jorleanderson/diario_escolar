package com.escola.diario_escolar.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.escola.diario_escolar.dto.AlunoDto;
import com.escola.diario_escolar.dto.AlunoPatchDTO;
import com.escola.diario_escolar.exception.ApiException;
import com.escola.diario_escolar.mapper.AlunoMapper;
import com.escola.diario_escolar.model.AlunoEntity;
import com.escola.diario_escolar.repository.AlunoRepository;

@Service
public class AlunoService extends BaseService<AlunoEntity, Long>{
	
	private final AlunoRepository alunoRepository;

	public AlunoService(AlunoRepository repository) {
        super(repository, "Aluno"); 
		this.alunoRepository = repository;
    }

	public AlunoDto buscarPorId(Long id) {
		return AlunoMapper.toDto(findEntityById(id));
		
	}

	public AlunoDto criarAluno(AlunoDto dto) {

        if (alunoRepository.existsByEmail(dto.getEmail())) {
            throw new ApiException(
                "Já existe um aluno cadastrado com este e-mail",
                HttpStatus.CONFLICT
            );
        }

        if (alunoRepository.existsByMatricula(dto.getMatricula())) {
            throw new ApiException(
                "Já existe um aluno cadastrado com esta matrícula",
                HttpStatus.CONFLICT
            );
        }

        AlunoEntity aluno = AlunoMapper.toEntity(dto);
        AlunoEntity salvo = alunoRepository.save(aluno);

        return AlunoMapper.toDto(salvo);
    }


	public Page<AlunoDto> listarPaginado(Pageable pageable) {
	    return repository.findAll(pageable)
		.map(AlunoMapper::toDto);
	}

	public List<AlunoDto> listarTodos() {
		return repository.findAll()
				.stream()
				.map(AlunoMapper::toDto)
				.toList();
	}

	public AlunoDto atualizar(Long id, AlunoDto novoAluno) {

		AlunoEntity aluno = findEntityById(id);

		
		aluno.setDataNascimento(novoAluno.getDataNascimento());
		aluno.setEmail(novoAluno.getEmail());
		aluno.setMatricula(novoAluno.getMatricula());
		aluno.setNome(novoAluno.getNome());
		
		return AlunoMapper.toDto(repository.save(aluno));
	}



	public void deletar(Long id) {
		
		repository.deleteById(id);
	}

	public AlunoDto atualizarParcial(Long id, AlunoPatchDTO alunoPatch) {

        AlunoEntity aluno = findEntityById(id);

        if (alunoPatch.getNome() != null && !alunoPatch.getNome().isBlank()){
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

        return AlunoMapper.toDto(alunoAtualizado);
    }

}