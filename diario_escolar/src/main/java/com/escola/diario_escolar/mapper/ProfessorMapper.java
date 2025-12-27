package com.escola.diario_escolar.mapper;

import com.escola.diario_escolar.dto.ProfessorDTO;
import com.escola.diario_escolar.model.Professor;

public class ProfessorMapper {

    public static ProfessorDTO toDTO(Professor professor) {
        ProfessorDTO dto = new ProfessorDTO();
        dto.setId(professor.getId());
        dto.setNome(professor.getNome());
        dto.setEmail(professor.getEmail());
        dto.setDisciplina(professor.getDisciplina());
        dto.setFormacao(professor.getFormacao());
        return dto;
    }

    public static Professor toEntity(ProfessorDTO dto) {
        Professor professor = new Professor();
        professor.setId(dto.getId());
        professor.setNome(dto.getNome());
        professor.setEmail(dto.getEmail());
        professor.setDisciplina(dto.getDisciplina());
        professor.setFormacao(dto.getFormacao());
        return professor;
    }
}