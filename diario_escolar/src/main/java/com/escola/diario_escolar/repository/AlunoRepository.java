package com.escola.diario_escolar.repository;


import com.escola.diario_escolar.model.AlunoEntity;

public interface AlunoRepository extends BaseRepository <AlunoEntity, Long> {

    boolean existsByMatricula(String matricula);
}
