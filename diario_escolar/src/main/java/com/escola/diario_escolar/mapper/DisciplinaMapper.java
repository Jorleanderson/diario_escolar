package com.escola.diario_escolar.mapper;

import com.escola.diario_escolar.dto.disciplina.DisciplinaDto;
import com.escola.diario_escolar.dto.disciplina.DisciplinaResponseDto;
import com.escola.diario_escolar.dto.disciplina.DisciplinaResumoDto;
import com.escola.diario_escolar.dto.professor.ProfessorResumoDto;
import com.escola.diario_escolar.dto.turma.TurmaResumoDto;
import com.escola.diario_escolar.model.Disciplina;
import com.escola.diario_escolar.model.Professor;
import com.escola.diario_escolar.model.Turma;

public class DisciplinaMapper {

    public static DisciplinaDto toDto(Disciplina disciplina) {
        DisciplinaDto dto = new DisciplinaDto();
       
        dto.setNome(disciplina.getNome());
        dto.setCargaHoraria(disciplina.getCargaHoraria());
        return dto;
    }

    public static Disciplina toEntity(
        DisciplinaDto dto,
        Turma turma,
        Professor professor) {

            Disciplina disciplina = new Disciplina();
            disciplina.setNome(dto.getNome());
            disciplina.setCargaHoraria(dto.getCargaHoraria());
            disciplina.setTurma(turma);
            disciplina.setProfessor(professor);
            return disciplina;
    }

    public static DisciplinaResponseDto toResponseDto(Disciplina disciplina) {

    DisciplinaResponseDto dto = new DisciplinaResponseDto();

    dto.setId(disciplina.getId());
    dto.setNome(disciplina.getNome());
    dto.setCargaHoraria(disciplina.getCargaHoraria());

    TurmaResumoDto turmaDto = new TurmaResumoDto();
    turmaDto.setId(disciplina.getTurma().getId());
    turmaDto.setCodigo(disciplina.getTurma().getCodigo());
    turmaDto.setNome(disciplina.getTurma().getNome());
    dto.setTurma(turmaDto);

    ProfessorResumoDto profDto = new ProfessorResumoDto();
    profDto.setId(disciplina.getProfessor().getId());
    profDto.setNome(disciplina.getProfessor().getNome());
    profDto.setEmail(disciplina.getProfessor().getEmail());
    dto.setProfessor(profDto);

    return dto;
}

    public static DisciplinaResumoDto toResumo(Disciplina disciplina) {
        DisciplinaResumoDto dto = new DisciplinaResumoDto();
        dto.setId(disciplina.getId());
        dto.setNome(disciplina.getNome());
        return dto;
    }
}
