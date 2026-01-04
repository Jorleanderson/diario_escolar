package com.escola.diario_escolar.mapper;

import com.escola.diario_escolar.dto.NotaDto;
import com.escola.diario_escolar.model.Nota;

public class NotaMapper {

    public static NotaDto toDto(Nota nota) {
        NotaDto dto = new NotaDto();
        dto.setId(nota.getId());
        dto.setTrimestre(nota.getTrimestre());
        dto.setValor(nota.getValor());
        return dto;
    }

    public static Nota toEntity(NotaDto dto) {
        Nota nota = new Nota();
        nota.setId(dto.getId());
        nota.setTrimestre(dto.getTrimestre());
        nota.setValor(dto.getValor());
        return nota;
    }

}
