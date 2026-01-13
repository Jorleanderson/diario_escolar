package com.escola.diario_escolar.repository;

import java.util.List;

import com.escola.diario_escolar.model.Disciplina;

public interface DisciplinaRepository extends BaseRepository<Disciplina, Long> {
    boolean existsByNomeAndTurmaId(String nome, Long turmaId);

    List<Disciplina> findByTurmaId(Long turmaId);
}
