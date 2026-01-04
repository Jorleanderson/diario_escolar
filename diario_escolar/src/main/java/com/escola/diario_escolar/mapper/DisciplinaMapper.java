package com.escola.diario_escolar.mapper;

import com.escola.diario_escolar.dto.DisciplinaDto;
import com.escola.diario_escolar.model.Disciplina;

public class DisciplinaMapper {

    public static DisciplinaDto toDto(Disciplina disciplina) {
        DisciplinaDto dto = new DisciplinaDto();
        dto.setId(disciplina.getId());
        dto.setNome(disciplina.getNome());
        dto.setCargaHoraria(disciplina.getCargaHoraria());
        return dto;
    }

    public static Disciplina toEntity(DisciplinaDto dto) {
        Disciplina disciplina = new Disciplina();
        disciplina.setId(dto.getId());
        disciplina.setNome(dto.getNome());
        disciplina.setCargaHoraria(dto.getCargaHoraria());
        return disciplina;
    }

}
