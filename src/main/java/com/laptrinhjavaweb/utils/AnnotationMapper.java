package com.laptrinhjavaweb.utils;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.ArrayUtils;

import com.laptrinhjavaweb.annotation.*;

// get data from ResultSet then add to Entity 
public class AnnotationMapper<T> {
	public List<T> mapRow(ResultSet rs, Class<T> zClass) {
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

	public Field[] getAllFieldOfClass(Class<?> zClass) {
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
}
