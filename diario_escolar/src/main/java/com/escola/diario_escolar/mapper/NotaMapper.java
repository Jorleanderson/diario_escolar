package com.escola.diario_escolar.mapper;

import com.escola.diario_escolar.dto.aluno.AlunoResumoDto;
import com.escola.diario_escolar.dto.disciplina.DisciplinaResumoDto;
import com.escola.diario_escolar.dto.nota.NotaDto;
import com.escola.diario_escolar.dto.nota.NotaResponseDto;
import com.escola.diario_escolar.model.AlunoEntity;
import com.escola.diario_escolar.model.Disciplina;
import com.escola.diario_escolar.model.Nota;

public class NotaMapper {

    public static NotaDto toDto(Nota nota) {
        NotaDto dto = new NotaDto();
        dto.setId(nota.getId());
        dto.setTrimestre(nota.getTrimestre());
        dto.setValor(nota.getValor());
        return dto;
    }

    public static Nota toEntity(
        NotaDto dto,
        AlunoEntity aluno,
        Disciplina disciplina) {

        Nota nota = new Nota();
        nota.setTrimestre(dto.getTrimestre());
        nota.setValor(dto.getValor());
        nota.setAluno(aluno);
        nota.setDisciplina(disciplina);
        return nota;
    }

    public static NotaResponseDto toResponseDto(Nota nota) {

    NotaResponseDto dto = new NotaResponseDto();

    dto.setId(nota.getId());
    dto.setTrimestre(nota.getTrimestre());
    dto.setValor(nota.getValor());

    AlunoResumoDto alunoDto = new AlunoResumoDto();
    alunoDto.setId(nota.getAluno().getId());
    alunoDto.setNome(nota.getAluno().getNome());
    dto.setAluno(alunoDto);

    DisciplinaResumoDto discDto = new DisciplinaResumoDto();
    discDto.setId(nota.getDisciplina().getId());
    discDto.setNome(nota.getDisciplina().getNome());
    dto.setDisciplina(discDto);

    return dto;
}



}
