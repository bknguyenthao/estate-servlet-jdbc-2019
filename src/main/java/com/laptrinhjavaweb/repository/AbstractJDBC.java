package com.laptrinhjavaweb.repository;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;

import com.laptrinhjavaweb.annotation.Column;
import com.laptrinhjavaweb.annotation.Table;
import com.laptrinhjavaweb.paging.Pageble;
import com.laptrinhjavaweb.paging.Sorter;
import com.laptrinhjavaweb.utils.AnnotationMapper;
import com.laptrinhjavaweb.utils.CustomMapper;

public class AbstractJDBC<T> implements GenericJDBC<T> {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/estate";
	static final String USER = "root";
	static final String PASS = "ToTo20112213";

	private Class<T> zClass;

	@SuppressWarnings("unchecked")
	public AbstractJDBC() {
		Type type = getClass().getGenericSuperclass();
		ParameterizedType parameterizedType = (ParameterizedType) type;
		zClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];

	}

	public List<T> selectAnnotationWay(String sql, Object... objects) {
		AnnotationMapper<T> annotationMapper = new AnnotationMapper<>();
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			if (conn != null) {
				statement = conn.prepareStatement(sql);
				for (int i = 0; i < objects.length; i++) {
					statement.setObject(i + 1, objects[i]);
				}
				resultSet = statement.executeQuery();
				return annotationMapper.mapRow(resultSet, this.zClass);
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

	public List<T> selectCustomWay(String sql, Object... objects) {
		CustomMapper<T> customMapper = new CustomMapper<>();
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			if (conn != null) {
				statement = conn.prepareStatement(sql);
				for (int i = 0; i < objects.length; i++) {
					statement.setObject(i + 1, objects[i]);
				}
				resultSet = statement.executeQuery();
				return customMapper.mapRow(resultSet, this.zClass);
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
		System.out.println("Goodbye!");
		return null;
	}
	// insert by old way
//	public Long insert(String sql, Object... objects) {
//		Connection conn = null;
//		PreparedStatement statement = null;
//		ResultSet resultSet = null;
//		try {
//			Class.forName(JDBC_DRIVER);
//			conn = DriverManager.getConnection(DB_URL, USER, PASS);
//			conn.setAutoCommit(false);
//			if (conn != null) {
//				statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//				for (int i = 0; i < objects.length; i++) {
//					statement.setObject(i + 1, objects[i]);
//				}
//				statement.executeUpdate();
//				conn.commit();
//				resultSet = statement.getGeneratedKeys();
//				while (resultSet.next()) {
//					Long id = resultSet.getLong(1);
//					return id;
//				}
//			}
//		} catch (Exception e) {
//			try {
//				conn.rollback();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//		} finally {
//			try {
//				conn.close();
//				statement.close();
//				resultSet.close();
//			} catch (SQLException se) {
//				se.printStackTrace();
//			}
//		}
//		return null;
//	}

//	public void updateOrDelete(String sql, Object... objects) {
//		Connection conn = null;
//		PreparedStatement statement = null;
//		try {
//			Class.forName(JDBC_DRIVER);
//			conn = DriverManager.getConnection(DB_URL, USER, PASS);
//			conn.setAutoCommit(false);
//			if (conn != null) {
//				statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//				for (int i = 0; i < objects.length; i++) {
//					statement.setObject(i + 1, objects[i]);
//				}
//				statement.executeUpdate();
//				conn.commit();
//			}
//		} catch (Exception e) {
//			try {
//				conn.rollback();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//		} finally {
//			try {
//				conn.close();
//				statement.close();
//			} catch (SQLException se) {
//				se.printStackTrace();
//			}
//		}
//	}

	@Override
	public Long insert(Object object) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			conn.setAutoCommit(false);
			Class<?> zClass = object.getClass();
			String sql = createSQLInsert(zClass);
			if (conn != null) {
				statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				Field[] fields = getAllFieldOfClass(zClass);
				for (int i = 0; i < fields.length; i++) {
					Field field = fields[i];
					field.setAccessible(true);
//					if (field.get(object) != null) {
					statement.setObject(i + 1, field.get(object));
//					}
				}
				statement.executeUpdate();
				conn.commit();
				resultSet = statement.getGeneratedKeys();
				while (resultSet.next()) {
					Long id = resultSet.getLong(1);
					return id;
				}
			}
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
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

	@Override
	public void update(Object object) {
		Connection conn = null;
		PreparedStatement statement = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			conn.setAutoCommit(false);
			Class<?> zClass = object.getClass();
			String sql = createSQLUpdate(zClass);
			if (conn != null) {
				statement = conn.prepareStatement(sql);
				Field[] fields = getAllFieldOfClass(zClass);
				Object id = null;
				for (int i = 0; i < fields.length; i++) {
					Field field = fields[i];
					field.setAccessible(true);
					if (!field.getName().equals("id")) {
						statement.setObject(i + 1, field.get(object));
					} else {
						id = field.get(object);
					}
				}
				statement.setObject(fields.length, id);
				statement.executeUpdate();
				conn.commit();
			}
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.close();
				statement.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	@Override
	public void delete(Long id) {
		Connection conn = null;
		PreparedStatement statement = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			conn.setAutoCommit(false);
			String sql = createSQLDelete();
			if (conn != null) {
				statement = conn.prepareStatement(sql);
				statement.setObject(1, id);
				statement.executeUpdate();
				conn.commit();
			}
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.close();
				statement.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	@Override
	public T findById(Long id) {
		AnnotationMapper<T> annotationMapper = new AnnotationMapper<>();
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			String sql = createSQLSelect();
			if (conn != null) {
				statement = conn.prepareStatement(sql);
				statement.setObject(1, id);
				resultSet = statement.executeQuery();
				return annotationMapper.mapRow(resultSet, this.zClass).get(0);
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

	@Override
	public List<T> findAll(Map<String, Object> properties, Pageble pageble, Object... where) {
		AnnotationMapper<T> annotationMapper = new AnnotationMapper<>();
		Connection conn = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			StringBuilder sql = createSQLFindAll(properties);
			if (where != null && where.length > 0) {
				sql.append(where[0]);
			}
			if (pageble != null) {
				if (pageble.getSorter() != null) {
					Sorter sorter = pageble.getSorter();
					sql.append(" order by " + sorter.getSortName() + " " + sorter.getSortBy() + "");
				}
				if (pageble.getOffset() != null && pageble.getLimit() != null) {
					sql.append(" limit" + pageble.getOffset() + ", " + pageble.getLimit() + "");
				}
			}
			if (conn != null) {
				statement = conn.createStatement();
				resultSet = statement.executeQuery(sql.toString());
				return annotationMapper.mapRow(resultSet, this.zClass);
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

	private StringBuilder createSQLFindAll(Map<String, Object> properties) {
		String tableName = "";
		if (this.zClass.isAnnotationPresent(Table.class)) {
			Table table = this.zClass.getAnnotation(Table.class);
			tableName = table.name();
		}
		StringBuilder result = new StringBuilder(
				"select * from " + tableName + " " + tableName.substring(0, 1) + " where 1 = 1 ");
		if (properties != null && properties.size() > 0) {
			String[] params = new String[properties.size()];
			Object[] values = new Object[properties.size()];
			int j = 0;
			for (Map.Entry<?, ?> item : properties.entrySet()) {
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
		return result;
	}

	private String createSQLSelect() {
		String sqlReturn = "select * from ";
		String tableName = "";
		if (this.zClass.isAnnotationPresent(Table.class)) {
			Table table = this.zClass.getAnnotation(Table.class);
			tableName = table.name();
		}
		sqlReturn += tableName + " where id = ?";
		return sqlReturn;
	}

	private String createSQLDelete() {
		String sqlReturn = "delete from ";
		String tableName = "";
		if (this.zClass.isAnnotationPresent(Table.class)) {
			Table table = this.zClass.getAnnotation(Table.class);
			tableName = table.name();
		}
		sqlReturn += tableName + " where id = ?";
		return sqlReturn;
	}

	private String createSQLUpdate(Class<?> zClass) {
		// Update table set name = ?, ward = ? where id = ?
		String sqlReturn = "update ";

		String tableName = "";
		if (zClass.isAnnotationPresent(Table.class)) {
			Table table = zClass.getAnnotation(Table.class);
			tableName = table.name();
		}
		sqlReturn += tableName + " set ";
		Field[] fields = getAllFieldOfClass(zClass);
		for (Field field : fields) {
			if (field.isAnnotationPresent(Column.class)) {
				Column column = field.getAnnotation(Column.class);
				if (!column.name().equals("id")) {
					sqlReturn += column.name() + " = ?, ";
				}
			}
		}
		sqlReturn = sqlReturn.substring(0, sqlReturn.length() - 2);
		sqlReturn += " where id = ?";
		return sqlReturn;
	}

	public String createSQLInsert(Class<?> zClass) {
		String sqlReturn = "insert into ";

		String tableName = "";
		if (zClass.isAnnotationPresent(Table.class)) {
			Table table = zClass.getAnnotation(Table.class);
			tableName = table.name();
		}
		sqlReturn += tableName + "(";
		Field[] fields = getAllFieldOfClass(zClass);
		int count = 0;
		for (Field field : fields) {
			if (field.isAnnotationPresent(Column.class)) {
				Column column = field.getAnnotation(Column.class);
//				if (!column.name().toString().equals("id")) {
				sqlReturn += column.name() + ",";
				count++;
//				}
			}
		}
		sqlReturn = sqlReturn.substring(0, sqlReturn.length() - 1);
		sqlReturn += ") values(";
		for (int i = 0; i < count; i++) {
			sqlReturn += "?,";
		}
		sqlReturn = sqlReturn.substring(0, sqlReturn.length() - 1);
		sqlReturn += ")";
		return sqlReturn;
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
