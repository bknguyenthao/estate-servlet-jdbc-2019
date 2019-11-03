package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.paging.Pageble;

public interface IBuildingService {
	BuildingDTO save(BuildingDTO buildingDTO);

	void update(BuildingDTO buildingDTO);

	void delete(Long id);

	BuildingDTO findById(Long id);

	List<BuildingDTO> findAll(BuildingSearchBuilder buildingSearchBuilder, Pageble pageble);
}
