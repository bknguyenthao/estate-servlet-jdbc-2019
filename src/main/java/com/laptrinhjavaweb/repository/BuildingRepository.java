package com.laptrinhjavaweb.repository;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.paging.Sorter;
import com.laptrinhjavaweb.utils.Static;

public class BuildingRepository extends BaseRepository<BuildingEntity> implements IBuildingRepository {

	@Override
	public List<BuildingEntity> searchBuilding(BuildingSearchBuilder buildingSearchBuilder, Pageable pageable) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			Class.forName(Static.JDBC_DRIVER);
			conn = DriverManager.getConnection(Static.DB_URL, Static.USER, Static.PASS);
			String sql = createSQLSearchBuilding(buildingSearchBuilder, pageable);
			if (conn != null) {
				statement = conn.prepareStatement(sql);
				resultSet = statement.executeQuery();
				return Static.mapRowByAnotation(resultSet, BuildingEntity.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				statement.close();
				resultSet.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return null;
	}

	private String createSQLSearchBuilding(BuildingSearchBuilder buildingSearchBuilder, Pageable pageable) {
		StringBuilder result = new StringBuilder("select * from building bd where 1 = 1 ");
		// fields with search normal
		Map<String, Object> condition = getCondition(buildingSearchBuilder);
		if (condition != null && condition.size() > 0) {
			String[] params = new String[condition.size()];
			Object[] values = new Object[condition.size()];
			int j = 0;
			for (Map.Entry<?, ?> item : condition.entrySet()) {
				params[j] = (String) item.getKey();
				values[j] = item.getValue();
				j++;
			}
			for (int i = 0; i < params.length; i++) {
				if (values[i] instanceof String && values[i].toString() != null
						&& !values[i].toString().trim().isEmpty()) {
					result.append(" and lower(" + params[i] + ") like '%" + values[i].toString().toLowerCase() + "%' ");
				} else if (values[i] instanceof Integer) {
					result.append(" and " + params[i] + " = " + values[i] + " ");
				} else if (values[i] instanceof Long) {
					result.append(" and " + params[i] + " = " + values[i] + " ");
				}
			}
		}
		// fiels search difficult: costrent, rentarea, buildingtype
		if (StringUtils.isNotBlank(buildingSearchBuilder.getCostRentFrom())) {
			result.append(" and costrent >= '" + buildingSearchBuilder.getCostRentFrom() + "' ");
		}
		if (buildingSearchBuilder.getCostRentTo() != null && !buildingSearchBuilder.getCostRentTo().trim().isEmpty()) {
			result.append(" and costrent <= '" + buildingSearchBuilder.getCostRentTo() + "' ");
		}
		if (StringUtils.isNotBlank(buildingSearchBuilder.getAreaRentFrom())
				|| StringUtils.isNotBlank(buildingSearchBuilder.getAreaRentTo())) {
			result.append(" and exists (select * from rentarea ra where (ra.buildingid = bd.id");
			if (StringUtils.isNotBlank(buildingSearchBuilder.getAreaRentFrom())) {
				result.append(" and ra.value >= '" + buildingSearchBuilder.getAreaRentFrom() + "' ");
			}
			if (StringUtils.isNotBlank(buildingSearchBuilder.getAreaRentTo())) {
				result.append(" and ra.value <= '" + buildingSearchBuilder.getAreaRentTo() + "' ))");
			}
		}
		if (buildingSearchBuilder.getBuildingTypes() != null && buildingSearchBuilder.getBuildingTypes().length > 0) {
			result.append(" and (buildingtype like '%" + buildingSearchBuilder.getBuildingTypes()[0] + "%' ");
			for (int i = 1; i < buildingSearchBuilder.getBuildingTypes().length; i++) {
				result.append(" or buildingtype like '%" + buildingSearchBuilder.getBuildingTypes()[i] + "%' ");
			}
			result.append(" )");
		}

		// paging and sorter
		if (pageable != null) {
			if (pageable.getSorter() != null) {
				Sorter sorter = pageable.getSorter();
				result.append(" order by " + sorter.getSortName() + " " + sorter.getSortBy() + " ");
			}
			if (pageable.getOffset() != null && pageable.getLimit() != null) {
				result.append(" limit" + pageable.getOffset() + ", " + pageable.getLimit() + " ");
			}
		}
		return result.toString();
	}

	private Map<String, Object> getCondition(BuildingSearchBuilder buildingSearchBuilder) {
		Map<String, Object> condition = new HashMap<String, Object>();
		Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
		for (Field field : fields) {
			boolean b1 = !field.getName().equals("buildingTypes");
			boolean b2 = !field.getName().equals("costRentFrom");
			boolean b3 = !field.getName().equals("costRentTo");
			boolean b4 = !field.getName().equals("areaRentFrom");
			boolean b5 = !field.getName().equals("areaRentTo");
			if (b1 && b2 && b3 && b4 && b5) {
				try {
					field.setAccessible(true);
					Object tmp = field.get(buildingSearchBuilder);
					if (tmp != null && tmp.toString().trim().length() > 0) {
						boolean b6 = field.getName().equals("numberOfBasement");
						boolean b7 = field.getName().equals("buildingArea");
						if (b6 || b7) {
							condition.put(field.getName().toLowerCase(), Integer.valueOf(tmp.toString()));
						} else {
							condition.put(field.getName().toLowerCase(), tmp);
						}
					}
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return condition;
	}

}
