package com.escola.diario_escolar.repository;

import com.escola.diario_escolar.model.Professor;

public interface ProfessorRepository extends BaseRepository<Professor, Long> {
boolean existsByEmail(String email);
  
}
