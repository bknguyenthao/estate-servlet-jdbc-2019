package com.laptrinhjavaweb.repository;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.paging.Pageble;

public class BuildingRepository extends AbstractJDBC<BuildingEntity> implements IBuildingRepository {

	@Override
	public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder, Pageble pageble) {
		Map<String, Object> mapSearch = buildMapSearch(buildingSearchBuilder);
		StringBuilder whereClause = new StringBuilder();
		if (buildingSearchBuilder.getCostRentFrom() != null
				&& !buildingSearchBuilder.getCostRentFrom().trim().isEmpty()) {
			whereClause.append(" and costrent >= '" + buildingSearchBuilder.getCostRentFrom() + "' ");
		}
		if (buildingSearchBuilder.getCostRentTo() != null && !buildingSearchBuilder.getCostRentTo().trim().isEmpty()) {
			whereClause.append(" and costrent <= '" + buildingSearchBuilder.getCostRentTo() + "' ");
		}
		if ((buildingSearchBuilder.getAreaFrom() != null && !buildingSearchBuilder.getAreaFrom().trim().isEmpty())
				|| (buildingSearchBuilder.getAreaTo() != null && !buildingSearchBuilder.getAreaTo().trim().isEmpty())) {
			whereClause.append(" and exists (select * from rentarea ra where (ra.buildingid = b.id");
			if (buildingSearchBuilder.getAreaFrom() != null) {
				whereClause.append(" and ra.value >= '" + buildingSearchBuilder.getAreaFrom() + "' ");
			}
			if (buildingSearchBuilder.getAreaTo() != null) {
				whereClause.append(" and ra.value <= '" + buildingSearchBuilder.getAreaTo() + "' ))");
			}
		}
		if (buildingSearchBuilder.getBuildingTypes() != null && buildingSearchBuilder.getBuildingTypes().length > 0) {
			whereClause.append(" and (b.buildingtype like '%" + buildingSearchBuilder.getBuildingTypes()[0] + "%' ");
			for (int i = 1; i < buildingSearchBuilder.getBuildingTypes().length; i++) {
				whereClause.append(" or b.buildingtype like '%" + buildingSearchBuilder.getBuildingTypes()[i] + "%' ");
			}
			whereClause.append(" )");
		}
		return findAll(mapSearch, pageble, whereClause.toString());
	}

	private Map<String, Object> buildMapSearch(BuildingSearchBuilder buildingSearchBuilder) {
		Map<String, Object> mapSearch = new HashMap<String, Object>();
		Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
		for (Field field : fields) {
			if (!field.getName().equals("buildingTypes") && !field.getName().startsWith("costRent")
					&& !field.getName().startsWith("area")) {
				try {
					field.setAccessible(true);
					Object tmp = field.get(buildingSearchBuilder);
					if (tmp != null) {
						mapSearch.put(field.getName().toLowerCase(), tmp);
					}
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return mapSearch;
	}

}
