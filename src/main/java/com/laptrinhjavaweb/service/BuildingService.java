package com.laptrinhjavaweb.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.repository.IBuildingRepository;
import com.laptrinhjavaweb.repository.IRentAreaRepository;

public class BuildingService implements IBuildingService {

	@Inject
	private IBuildingRepository buildingRepository;

	@Inject
	private BuildingConverter buildingConverter;

	@Inject
	private IRentAreaRepository rentAreaRepository;

	@Override
	public Long insert(BuildingDTO buildingDTO) {
		BuildingEntity buildingEntity = buildingConverter.convertToEntity(buildingDTO);
		buildingEntity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		buildingEntity.setCreatedBy("");
		buildingEntity.setBuildingType(StringUtils.join(buildingDTO.getBuildingTypes(), ","));
		Long id = buildingRepository.insert(buildingEntity);
		// Save rentarea
		for (String item : buildingDTO.getRentArea().split(",")) {
			RentAreaEntity rentAreaEntity = new RentAreaEntity();
			rentAreaEntity.setValue(item);
			rentAreaEntity.setBuildingId(id);
			rentAreaRepository.insert(rentAreaEntity);
		}
		return id;
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
	public List<BuildingDTO> findAll() {
		try {
			List<BuildingEntity> buildingEntitys = buildingRepository.findAll();
			return buildingEntitys.stream().map(item -> buildingConverter.convertToDTO(item))
					.collect(Collectors.toList());
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<BuildingDTO> findByCondition(Map<String, Object> condition) {
		try {
			List<BuildingEntity> buildingEntitys = buildingRepository.findByCondition(condition);
			return buildingEntitys.stream().map(item -> buildingConverter.convertToDTO(item))
					.collect(Collectors.toList());
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<BuildingDTO> searchBuilding(BuildingSearchBuilder buildingSearchBuilder, Pageable pageble) {
		try {
			List<BuildingEntity> buildingEntitys = buildingRepository.searchBuilding(buildingSearchBuilder, pageble);
			return buildingEntitys.stream().map(item -> buildingConverter.convertToDTO(item))
					.collect(Collectors.toList());
		} catch (Exception e) {
			return null;
		}
	}

}
