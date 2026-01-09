package com.escola.diario_escolar.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.escola.diario_escolar.model.AlunoEntity;

public interface AlunoRepository extends BaseRepository<AlunoEntity, Long> {

    boolean existsByEmail(String email);

    boolean existsByMatricula(String matricula);

    List<AlunoEntity> findByTurmaId(Long turmaId);

    Page<AlunoEntity> findByTurmaId(Long turmaId, Pageable pageable);
}
