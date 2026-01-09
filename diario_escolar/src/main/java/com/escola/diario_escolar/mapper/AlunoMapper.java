package com.escola.diario_escolar.mapper;

import com.escola.diario_escolar.dto.aluno.AlunoDto;
import com.escola.diario_escolar.dto.aluno.AlunoResponseDto;
import com.escola.diario_escolar.dto.turma.TurmaResumoDto;
import com.escola.diario_escolar.model.AlunoEntity;
import com.escola.diario_escolar.model.Turma;

public class AlunoMapper {

    public static AlunoDto toDto(AlunoEntity aluno) {
        AlunoDto dto = new AlunoDto();

        dto.setId(aluno.getId());
        dto.setNome(aluno.getNome());
        dto.setMatricula(aluno.getMatricula());
        dto.setDataNascimento(aluno.getDataNascimento());
        dto.setEmail(aluno.getEmail());
        dto.setTurmaId(aluno.getTurma().getId());
        return dto;
    }

    public static AlunoEntity toEntity(AlunoDto dto, Turma turma) {
        AlunoEntity aluno = new AlunoEntity();
        aluno.setNome(dto.getNome());
        aluno.setMatricula(dto.getMatricula());
        aluno.setDataNascimento(dto.getDataNascimento());
        aluno.setEmail(dto.getEmail());
        aluno.setTurma(turma);
        return aluno;
    }

    public static AlunoResponseDto toResponseDto(AlunoEntity aluno) {
        AlunoResponseDto dto = new AlunoResponseDto();

        dto.setId(aluno.getId());
        dto.setNome(aluno.getNome());
        dto.setMatricula(aluno.getMatricula());
        dto.setDataNascimento(aluno.getDataNascimento());
        dto.setEmail(aluno.getEmail());

        TurmaResumoDto turmaResumoDto = new TurmaResumoDto();
        turmaResumoDto.setId(aluno.getTurma().getId());
        turmaResumoDto.setCodigo(aluno.getTurma().getCodigo());
        turmaResumoDto.setNome(aluno.getTurma().getNome());

        dto.setTurma(turmaResumoDto);

        return dto;
    }
}