package com.laptrinhjavaweb.service;

import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.paging.Pageable;

public interface IBuildingService {

	Long insert(BuildingDTO buildingDTO);

	void update(BuildingDTO buildingDTO);

	void delete(Long id);

	BuildingDTO findById(Long id);

	List<BuildingDTO> findAll();

	List<BuildingDTO> findByCondition(Map<String, Object> condition);

	List<BuildingDTO> searchBuilding(BuildingSearchBuilder buildingSearchBuilder, Pageable pageable);
}
