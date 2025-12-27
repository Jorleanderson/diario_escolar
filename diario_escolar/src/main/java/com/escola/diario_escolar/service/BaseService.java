package com.escola.diario_escolar.service;

import com.escola.diario_escolar.repository.BaseRepository;
import jakarta.persistence.EntityNotFoundException; //posso criar algo como o ProfessorNotFoundException

public abstract class BaseService<T, ID> {

   protected final BaseRepository<T, ID> repository;
    private final String nomeEntidade;

    // No construtor, pedimos o nome da entidade para personalizar o erro
    public BaseService(BaseRepository<T, ID> repository, String nomeEntidade) {
        this.repository = repository;
        this.nomeEntidade = nomeEntidade;
    }
    protected T findEntityById(ID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                    nomeEntidade + " não encontrado(a) com o ID: " + id
                ));
    }
}