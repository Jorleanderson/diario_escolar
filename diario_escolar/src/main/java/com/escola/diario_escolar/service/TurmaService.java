package com.escola.diario_escolar.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.escola.diario_escolar.dto.TurmaDto;
import com.escola.diario_escolar.dto.TurmaPatchDto;
import com.escola.diario_escolar.exception.ApiException;
import com.escola.diario_escolar.mapper.TurmaMapper;
import com.escola.diario_escolar.model.Turma;
import com.escola.diario_escolar.repository.TurmaRepository;

@Service
public class TurmaService extends BaseService<Turma, Long> {

   private final TurmaRepository repository;

        public TurmaService (TurmaRepository repository){
            super(repository,"Turma");
            this.repository = repository;
        }

        public TurmaDto criarTurma(TurmaDto turmaDto) {

            if (repository.existsByCodigo(turmaDto.getCodigo())) {
                throw new ApiException(
                    "Já existe uma turma cadastrada com este código",
                    HttpStatus.CONFLICT
                );
            }
            
            Turma turma = TurmaMapper.toEntity(turmaDto);
            Turma salvo = repository.save(turma);
            
            return TurmaMapper.toDto(salvo);
        }
        
        public List<TurmaDto> listarTodos() {
            
            return repository.findAll()
            .stream()
            .map(TurmaMapper::toDto)
            .toList();
        }

        public TurmaDto buscarPorId(Long id){
            return TurmaMapper.toDto(findEntityById(id));
        }

        public TurmaDto atualizar(TurmaDto atualizado, Long id){

            Turma turma= findEntityById(id);

            turma.setTurno(atualizado.getTurno());
            turma.setAnoLetivo(atualizado.getAnoLetivo());
            turma.setNome(atualizado.getNome());
            turma.setCodigo(atualizado.getCodigo());

            return TurmaMapper.toDto(turma);
        }

        public void deletar (long id){
            Turma turma = findEntityById(id);

            repository.delete(turma);
        }

        public TurmaDto atualizarParcial(Long id, TurmaPatchDto patchDto) {
            Turma turma = findEntityById(id);

            if (patchDto.getTurno() != null && !patchDto.getTurno().isBlank()) {
                turma.setTurno(patchDto.getTurno());
            }
            if (patchDto.getAnoLetivo() != null) {
                turma.setAnoLetivo(patchDto.getAnoLetivo());
            }
            if (patchDto.getNome() != null && !patchDto.getNome().isBlank()) {
                turma.setNome(patchDto.getNome());
            }
            if (patchDto.getCodigo() != null && !patchDto.getCodigo().isBlank()) {
                turma.setCodigo(patchDto.getCodigo());
            }

            return TurmaMapper.toDto(repository.save(turma));
        }

        public Page<TurmaDto> listarPaginado(Pageable pageable) {
            return repository.findAll(pageable)
            .map(TurmaMapper::toDto);
        }

}