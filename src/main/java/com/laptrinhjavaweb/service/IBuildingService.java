package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.dto.BuildingDTO;

public interface IBuildingService {
	BuildingDTO save(BuildingDTO buildingDTO);

	void update(BuildingDTO buildingDTO);

	void delete(BuildingDTO buildingDTO);

	BuildingDTO findById(Long id);

	List<BuildingDTO> findAll();
}
