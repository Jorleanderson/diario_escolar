package com.escola.diario_escolar.mapper;

import com.escola.diario_escolar.dto.AlunoDto;
import com.escola.diario_escolar.model.AlunoEntity;

public class AlunoMapper {

    public static AlunoDto toDto(AlunoEntity aluno) {
        AlunoDto dto = new AlunoDto();

        dto.setId(aluno.getId());
        dto.setNome(aluno.getNome());
        dto.setMatricula(aluno.getMatricula());
        dto.setDataNascimento(aluno.getDataNascimento());
        dto.setEmail(aluno.getEmail());
        return dto;
    }

    public static AlunoEntity toEntity(AlunoDto dto) {
        AlunoEntity aluno = new AlunoEntity();
        aluno.setNome(dto.getNome());
        aluno.setMatricula(dto.getMatricula());
        aluno.setDataNascimento(dto.getDataNascimento());
        aluno.setEmail(dto.getEmail());
        return aluno;
    }
}
