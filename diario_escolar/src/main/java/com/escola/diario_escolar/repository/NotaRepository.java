package com.escola.diario_escolar.repository;

import java.util.List;

import com.escola.diario_escolar.model.Nota;

public interface NotaRepository extends BaseRepository<Nota, Long> {

    boolean existsByAlunoIdAndDisciplinaIdAndTrimestre(
        Long alunoId,
        Long disciplinaId,
        Integer trimestre
    );

    List<Nota> findByAlunoId(Long alunoId);
}
