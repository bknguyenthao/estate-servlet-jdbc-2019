package com.laptrinhjavaweb.utils;

import org.modelmapper.ModelMapper;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;

// convert BuildingDTO <-> BuildingEntity
public class BuildingConverter {
	public BuildingDTO convertToDTO(BuildingEntity buildingEntity) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(buildingEntity, BuildingDTO.class);
	}

	public BuildingEntity convertToEntity(BuildingDTO buildingDTO) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(buildingDTO, BuildingEntity.class);
	}
}
