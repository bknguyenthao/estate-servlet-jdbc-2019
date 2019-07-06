package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.BuildingDTO;

public interface IBuildingService {
	BuildingDTO save(BuildingDTO buildingDTO);

	void update(BuildingDTO buildingDTO);

	void delete(BuildingDTO buildingDTO);

	BuildingDTO findById(Long id);
}
