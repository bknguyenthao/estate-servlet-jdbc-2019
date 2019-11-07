package com.laptrinhjavaweb.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.laptrinhjavaweb.converter.RentAreaConverter;
import com.laptrinhjavaweb.dto.RentAreaDTO;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.repository.IRentAreaRepository;

public class RentAreaService implements IRentAreaService {

	@Inject
	private IRentAreaRepository rentAreaRepository;

	@Inject
	private RentAreaConverter rentAreaConverter;

	@Override
	public Long insert(RentAreaDTO rentAreaDTO) {
		RentAreaEntity rentAreaEntity = rentAreaConverter.convertToEntity(rentAreaDTO);
		Long id = rentAreaRepository.insert(rentAreaEntity);
		return id;
	}

	@Override
	public void update(RentAreaDTO rentAreaDTO) {
		RentAreaEntity rentAreaEntity = rentAreaConverter.convertToEntity(rentAreaDTO);
		rentAreaRepository.update(rentAreaEntity);

	}

	@Override
	public void delete(Long id) {
		rentAreaRepository.delete(id);

	}

	@Override
	public RentAreaDTO findById(Long id) {
		RentAreaEntity rentAreaEntity = rentAreaRepository.findById(id);
		if (rentAreaEntity == null) {
			return null;
		}
		RentAreaDTO rentAreaDTO = rentAreaConverter.convertToDTO(rentAreaEntity);
		return rentAreaDTO;
	}

	@Override
	public List<RentAreaDTO> findAll() {
		try {
			List<RentAreaEntity> rentAreaEntitys = rentAreaRepository.findAll();
			return rentAreaEntitys.stream().map(item -> rentAreaConverter.convertToDTO(item))
					.collect(Collectors.toList());
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<RentAreaDTO> findByCondition(Map<String, Object> condition) {
		try {
			List<RentAreaEntity> rentAreaEntitys = rentAreaRepository.findByCondition(condition);
			return rentAreaEntitys.stream().map(item -> rentAreaConverter.convertToDTO(item))
					.collect(Collectors.toList());
		} catch (Exception e) {
			return null;
		}
	}

}
