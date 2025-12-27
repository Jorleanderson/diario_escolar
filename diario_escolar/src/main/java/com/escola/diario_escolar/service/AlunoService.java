package com.escola.diario_escolar.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.escola.diario_escolar.dto.AlunoDto;
import com.escola.diario_escolar.mapper.AlunoMapper;
import com.escola.diario_escolar.model.AlunoEntity;
import com.escola.diario_escolar.repository.AlunoRepository;

@Service
public class AlunoService extends BaseService<AlunoEntity, Long>{
	
	public AlunoService(AlunoRepository repository) {
        super(repository, "Aluno"); 
    }

	public AlunoDto buscarPorId(Long id) {
		return AlunoMapper.toDto(findEntityById(id));
		
	}

	public AlunoDto criarAluno(AlunoDto dto) {
		AlunoEntity aluno = new AlunoEntity();
		aluno = repository.save(AlunoMapper.toEntity(dto));
		return AlunoMapper.toDto(aluno);
	}

	public List<AlunoEntity> listarTodos() {
	    return repository.findAll();
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

}