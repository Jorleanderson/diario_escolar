package com.escola.diario_escolar.service;

import org.springframework.http.HttpStatus;

import com.escola.diario_escolar.exception.ApiException;
import com.escola.diario_escolar.repository.BaseRepository;

public abstract class BaseService<T, ID> {

   protected final BaseRepository<T, ID> repository;
    private final String nomeEntidade;

    public BaseService(BaseRepository<T, ID> repository, String nomeEntidade) {
        this.repository = repository;
        this.nomeEntidade = nomeEntidade;
    }
    protected T findEntityById(ID id) {
        return repository.findById(id)
        .orElseThrow(() ->
            new ApiException(
                nomeEntidade + " não encontrado(a) com o ID: " + id,
                HttpStatus.NOT_FOUND
            )
        );
    }
}