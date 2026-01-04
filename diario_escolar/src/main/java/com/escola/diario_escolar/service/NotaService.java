package com.escola.diario_escolar.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.escola.diario_escolar.dto.NotaDto;
import com.escola.diario_escolar.dto.NotaPatchDto;
import com.escola.diario_escolar.mapper.NotaMapper;
import com.escola.diario_escolar.model.Nota;
import com.escola.diario_escolar.repository.NotaRepository;

@Service
public class NotaService extends BaseService<Nota, Long> {

	private final NotaRepository repository;

	public NotaService(NotaRepository repository) {
		super(repository, "Nota");
		this.repository = repository;
	}

	public NotaDto criarNota(NotaDto dto) {
		Nota nota = NotaMapper.toEntity(dto);

		return NotaMapper.toDto(repository.save(nota));
	}

	public List<NotaDto> listarTodos() {
		return repository.findAll()
				.stream()
				.map(NotaMapper::toDto)
				.toList();
	}

	public NotaDto buscarPorId(Long id) {
		return NotaMapper.toDto(findEntityById(id));
	}

	public NotaDto atualizar(Long id, NotaDto atualizado) {
		Nota nota = findEntityById(id);

		nota.setTrimestre(atualizado.getTrimestre());
		nota.setValor(atualizado.getValor());

		return NotaMapper.toDto(repository.save(nota));
	}

	public void deletar(Long id) {
		Nota nota = findEntityById(id);
		repository.delete(nota);
	}

	public NotaDto atualizarParcial(Long id, NotaPatchDto patchDto) {

		Nota nota = findEntityById(id);

		if (patchDto.getTrimestre() != null) {
			nota.setTrimestre(patchDto.getTrimestre());
		}

		if (patchDto.getValor() != null) {
			nota.setValor(patchDto.getValor());
		}

		Nota atualizado = repository.save(nota);

		return NotaMapper.toDto(atualizado);
	}

	public Page<NotaDto> listarPaginado(Pageable pageable) {
		return repository.findAll(pageable)
				.map(NotaMapper::toDto);
	}

}

