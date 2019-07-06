package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.utils.BuildingConverter;

import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.IBuildingRepository;

public class BuildingService implements IBuildingService {

	private IBuildingRepository buildingRepository;

	public BuildingService() {
		buildingRepository = new BuildingRepository();
	}

	@Override
	public BuildingDTO save(BuildingDTO buildingDTO) {
		BuildingConverter buildingConverter = new BuildingConverter();
		BuildingEntity buildingEntity = buildingConverter.convertToEntity(buildingDTO);
		Long id = buildingRepository.insert(buildingEntity);
		buildingDTO.setId(id);
		return buildingDTO;
	}

	@Override
	public void update(BuildingDTO buildingDTO) {
		BuildingConverter buildingConverter = new BuildingConverter();
		BuildingEntity buildingEntity = buildingConverter.convertToEntity(buildingDTO);
		buildingRepository.update(buildingEntity);
	}

	@Override
	public void delete(BuildingDTO buildingDTO) {
		BuildingConverter buildingConverter = new BuildingConverter();
		BuildingEntity buildingEntity = buildingConverter.convertToEntity(buildingDTO);
		buildingRepository.delete(buildingEntity);

	}

	@Override
	public BuildingDTO findById(Long id) {
		BuildingEntity buildingEntity = buildingRepository.findById(id);
		BuildingConverter buildingConverter = new BuildingConverter();
		BuildingDTO buildingDTO = buildingConverter.convertToDTO(buildingEntity);
		return buildingDTO;
	}

	@Override
	public List<BuildingDTO> findAll() {
		List<BuildingEntity> listBuildingEntity = buildingRepository.findAll();
		if (listBuildingEntity.size() == 0) {
			return null;
		}
		List<BuildingDTO> listBuildingDTO = new ArrayList<BuildingDTO>();
		BuildingConverter buildingConverter = new BuildingConverter();
		for (int i = 0; i < listBuildingEntity.size(); i++) {
			listBuildingDTO.add(buildingConverter.convertToDTO(listBuildingEntity.get(i)));
		}
		return listBuildingDTO;
	}

}
