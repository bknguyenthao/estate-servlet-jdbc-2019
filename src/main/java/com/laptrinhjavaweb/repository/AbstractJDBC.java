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

import org.apache.commons.lang3.ArrayUtils;

import com.laptrinhjavaweb.annotation.Column;
import com.laptrinhjavaweb.annotation.Table;
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
	public void delete(Object object) {
		Connection conn = null;
		PreparedStatement statement = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			conn.setAutoCommit(false);
			Class<?> zClass = object.getClass();
			String sql = createSQLDelete(zClass);
			if (conn != null) {
				statement = conn.prepareStatement(sql);
				Field[] fields = getAllFieldOfClass(zClass);
				for (int i = 0; i < fields.length; i++) {
					Field field = fields[i];
					field.setAccessible(true);
					if (field.getName().equals("id")) {
						statement.setObject(1, field.get(object));
						break;
					}
				}
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
	public List<T> findById(Long id) {
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

	private String createSQLDelete(Class<?> zClass) {
		String sqlReturn = "delete from ";
		String tableName = "";
		if (zClass.isAnnotationPresent(Table.class)) {
			Table table = zClass.getAnnotation(Table.class);
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
