package com.escola.diario_escolar.repository;

import java.util.UUID;

import com.escola.diario_escolar.model.Professor;

public interface ProfessorRepository extends BaseRepository<Professor, UUID> {
boolean existsByEmail(String email);
  
}
