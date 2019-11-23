package com.laptrinhjavaweb.converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.repository.IRentAreaRepository;

// convert BuildingDTO <-> BuildingEntity
public class BuildingConverter {

	@Inject
	private IRentAreaRepository rentAreaRepository;

	public BuildingDTO convertToDTO(BuildingEntity buildingEntity) {
		ModelMapper modelMapper = new ModelMapper();
		Map<String, Object> condition = new HashMap<>();
		condition.put("buildingid", buildingEntity.getId());

		List<RentAreaEntity> rentAreas = rentAreaRepository.findByCondition(condition);
		List<String> areas = new ArrayList<>();
		for (RentAreaEntity rentAreaEntity : rentAreas) {
			areas.add(rentAreaEntity.getValue());
		}
		BuildingDTO result = modelMapper.map(buildingEntity, BuildingDTO.class);
		if (areas.size() > 0) {
			result.setRentArea(StringUtils.join(areas, ","));
		}
		if(StringUtils.isNotBlank(buildingEntity.getBuildingType())) {
			result.setBuildingTypes(buildingEntity.getBuildingType().split(","));
		}
		return result;
	}

	public BuildingEntity convertToEntity(BuildingDTO buildingDTO) {
		ModelMapper modelMapper = new ModelMapper();
		BuildingEntity result = modelMapper.map(buildingDTO, BuildingEntity.class);
		if (StringUtils.isNotBlank(buildingDTO.getNumberOfBasement())) {
			result.setNumberOfBasement(Integer.parseInt(buildingDTO.getNumberOfBasement()));
		}
		if (StringUtils.isNotBlank(buildingDTO.getBuildingArea())) {
			result.setBuildingArea(Integer.parseInt(buildingDTO.getBuildingArea()));
		}
		return modelMapper.map(buildingDTO, BuildingEntity.class);
	}
}
