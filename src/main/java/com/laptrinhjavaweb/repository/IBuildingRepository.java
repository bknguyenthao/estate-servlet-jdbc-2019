package com.laptrinhjavaweb.repository;

import java.util.List;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.paging.Pageable;

public interface IBuildingRepository extends IBaseReposity<BuildingEntity> {
	List<BuildingEntity> searchBuilding(BuildingSearchBuilder buildingSearchBuilder, Pageable pageable);
}
