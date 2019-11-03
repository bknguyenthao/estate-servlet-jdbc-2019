package com.laptrinhjavaweb.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.paging.Pageble;
import com.laptrinhjavaweb.repository.IBuildingRepository;
import com.laptrinhjavaweb.utils.BuildingConverter;

public class BuildingService implements IBuildingService {

	@Inject
	private IBuildingRepository buildingRepository;

	@Inject
	private BuildingConverter buildingConverter;

	@Override
	public BuildingDTO save(BuildingDTO buildingDTO) {
		BuildingEntity buildingEntity = buildingConverter.convertToEntity(buildingDTO);
		Long id = buildingRepository.insert(buildingEntity);
		buildingDTO.setId(id);
		return buildingDTO;
	}

	@Override
	public void update(BuildingDTO buildingDTO) {
		BuildingEntity buildingEntity = buildingConverter.convertToEntity(buildingDTO);
		buildingRepository.update(buildingEntity);
	}

	@Override
	public void delete(Long id) {
		buildingRepository.delete(id);

	}

	@Override
	public BuildingDTO findById(Long id) {
		BuildingEntity buildingEntity = buildingRepository.findById(id);
		if (buildingEntity == null) {
			return null;
		}
		BuildingDTO buildingDTO = buildingConverter.convertToDTO(buildingEntity);
		return buildingDTO;
	}

	@Override
	public List<BuildingDTO> findAll(BuildingSearchBuilder buildingSearchBuilder, Pageble pageble) {
		try {
			List<BuildingEntity> buildingEntitys = buildingRepository.findAll(buildingSearchBuilder, pageble);
			return buildingEntitys.stream().map(item -> buildingConverter.convertToDTO(item))
					.collect(Collectors.toList());
		} catch (Exception e) {
			return null;
		}
	}

}
