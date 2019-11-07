package com.laptrinhjavaweb.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.ArrayUtils;

import com.laptrinhjavaweb.annotation.Column;
import com.laptrinhjavaweb.annotation.Entity;

public class Static {
	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/estate";
	public static final String USER = "root";
	public static final String PASS = "ToTo20112213";

	public static Field[] getAllFieldOfClass(Class<?> zClass) {
		if (zClass == null) {
			return null;
		}
		Field[] results = null;
		List<Class<?>> listClassAndParent = new ArrayList<Class<?>>();
		listClassAndParent.add(zClass);
		Class<?> parent = zClass.getSuperclass();
		// get all parent of class
		while (parent != null) {
			listClassAndParent.add(parent);
			parent = parent.getSuperclass();
		}
		// get all field in class and parent
		for (int i = 0; i < listClassAndParent.size(); i++) {
			results = ArrayUtils.addAll(results, listClassAndParent.get(i).getDeclaredFields());
		}

		return results;
	}

	public static Map<String, String> getDistrict() {
		Map<String, String> result = new HashMap<String, String>();
		result.put("QUAN_3", "Quận 3");
		result.put("QUAN_2", "Quận 2");
		result.put("QUAN_1", "Quận 1");
		return result;
	}

	public static Map<String, String> getBuildingType() {
		Map<String, String> result = new HashMap<String, String>();
		result.put("TANG_TRET", "Tầng trệt");
		result.put("NGUYEN_CAN", "Nguyên căn");
		result.put("NOI_THAT", "Nội thất");
		return result;
	}

	// Read request type JSON then convert to string
	public static String getStringFromRequestJSON(HttpServletRequest request) throws IOException {
		StringBuffer sb = new StringBuffer();
		String line = null;
		BufferedReader reader;
		reader = request.getReader();
		line = reader.readLine();
		while (line != null) {
			sb.append(line);
			line = reader.readLine();
		}
		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	// Read data from request then add to Object
	public static <T> T readDataFromRequest(Class<T> clazz, HttpServletRequest request) {
		T object = null;
		try {
			object = clazz.newInstance();
			BeanUtils.populate(object, request.getParameterMap());
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			System.out.print(e.getMessage());
		}
		return object;
	}

	// Read data from ResultSet then map to Object use anotation
	public static <T> List<T> mapRowByAnotation(ResultSet rs, Class<T> zClass) {
		List<T> results = new ArrayList<>();
		if (zClass.isAnnotationPresent(Entity.class)) {
			try {
				ResultSetMetaData resultSetMetaData = rs.getMetaData();
				Field[] fields = getAllFieldOfClass(zClass);
				while (rs.next()) {
					T object = (T) zClass.newInstance();
					// get value of 1 row in resultset then set into entity
					for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
						String columnName = resultSetMetaData.getColumnName(i + 1);// name column table sql
						Object columnValue = rs.getObject(i + 1);// value of column table sql
						for (Field field : fields) {
							if (field.isAnnotationPresent(Column.class)) {
								Column column = field.getAnnotation(Column.class);// ex: @Column(name = "name")...
								if (column.name().equals(columnName) && columnValue != null) {
									BeanUtils.setProperty(object, field.getName(), columnValue);
									break;
								}
							}
						}
					}
					// finish get value of 1 row in resultset then set into entity
					results.add(object);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return results;
	}

	// Read data from ResultSet then map to Object use anotation
	public static <T> List<T> mapRow(ResultSet rs, Class<T> zClass) {
		List<T> results = new ArrayList<>();
		try {
			ResultSetMetaData resultSetMetaData = rs.getMetaData();// ResultSetMetaData to get name of column table sql
			Field[] fields = Static.getAllFieldOfClass(zClass);
			while (rs.next()) {
				T object = (T) zClass.newInstance();
				// get value of 1 row in resultset then set into entity
				for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
					String columnName = resultSetMetaData.getColumnName(i + 1);// name column table sql
					Object columnValue = rs.getObject(i + 1);// value of column table sql
					for (Field field : fields) {
						if (field.getName().toLowerCase().equals(columnName) && columnValue != null) {
							BeanUtils.setProperty(object, field.getName(), columnValue);
							break;
						}
					}
				}
				// finish get value of 1 row in resultset then set into entity
				results.add(object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}
}
