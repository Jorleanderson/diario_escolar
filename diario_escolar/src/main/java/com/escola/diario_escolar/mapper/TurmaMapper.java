package com.escola.diario_escolar.mapper;

import com.escola.diario_escolar.dto.turma.TurmaDto;
import com.escola.diario_escolar.model.Turma;

public class TurmaMapper {
    
    public static Turma toEntity(TurmaDto dto){

        Turma turma = new Turma();

        turma.setAnoLetivo(dto.getAnoLetivo());
        turma.setCodigo(dto.getCodigo());
        turma.setId(dto.getId());
        turma.setNome(dto.getNome());
        turma.setTurno(dto.getTurno());

        return turma;
    }

    public static TurmaDto toDto(Turma turma){
        TurmaDto dto = new TurmaDto();

        dto.setAnoLetivo(turma.getAnoLetivo());
        dto.setCodigo(turma.getCodigo());
        dto.setId(turma.getId());
        dto.setNome(turma.getNome());
        dto.setTurno(turma.getTurno());

        return dto;
    }
}
