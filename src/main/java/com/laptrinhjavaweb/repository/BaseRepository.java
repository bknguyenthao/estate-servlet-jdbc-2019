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
import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.annotation.Column;
import com.laptrinhjavaweb.annotation.Table;
import com.laptrinhjavaweb.utils.Static;

public class BaseRepository<T> implements IBaseReposity<T> {

	private Class<T> zClass;

	@SuppressWarnings("unchecked")
	public BaseRepository() {
		Type type = getClass().getGenericSuperclass();
		ParameterizedType parameterizedType = (ParameterizedType) type;
		zClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
	}

	@Override
	public Long insert(Object object) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			Class.forName(Static.JDBC_DRIVER);
			conn = DriverManager.getConnection(Static.DB_URL, Static.USER, Static.PASS);
			conn.setAutoCommit(false);
			Class<?> zClass = object.getClass();
			String sql = createSQLInsert(zClass);
			if (conn != null) {
				statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				Field[] fields = Static.getAllFieldOfClass(zClass);
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
			Class.forName(Static.JDBC_DRIVER);
			conn = DriverManager.getConnection(Static.DB_URL, Static.USER, Static.PASS);
			conn.setAutoCommit(false);
			Class<?> zClass = object.getClass();
			String sql = createSQLUpdate(zClass);
			if (conn != null) {
				statement = conn.prepareStatement(sql);
				Field[] fields = Static.getAllFieldOfClass(zClass);
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
			Class.forName(Static.JDBC_DRIVER);
			conn = DriverManager.getConnection(Static.DB_URL, Static.USER, Static.PASS);
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
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			Class.forName(Static.JDBC_DRIVER);
			conn = DriverManager.getConnection(Static.DB_URL, Static.USER, Static.PASS);
			String sql = createSQLFindById();
			if (conn != null) {
				statement = conn.prepareStatement(sql);
				statement.setObject(1, id);
				resultSet = statement.executeQuery();
				return Static.mapRowByAnotation(resultSet, this.zClass).get(0);
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
	public List<T> findAll() {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			Class.forName(Static.JDBC_DRIVER);
			conn = DriverManager.getConnection(Static.DB_URL, Static.USER, Static.PASS);
			String sql = createSQLFindAll();
			if (conn != null) {
				statement = conn.prepareStatement(sql);
				resultSet = statement.executeQuery();
				return Static.mapRowByAnotation(resultSet, this.zClass);
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
	public List<T> findByCondition(Map<String, Object> condition) {
		Connection conn = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			Class.forName(Static.JDBC_DRIVER);
			conn = DriverManager.getConnection(Static.DB_URL, Static.USER, Static.PASS);

			StringBuilder sql = createSQLFindByCondition(condition);
			if (conn != null) {
				statement = conn.createStatement();
				resultSet = statement.executeQuery(sql.toString());
				return Static.mapRowByAnotation(resultSet, this.zClass);
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

	private String createSQLFindById() {
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
		Field[] fields = Static.getAllFieldOfClass(zClass);
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
		Field[] fields = Static.getAllFieldOfClass(zClass);
		int count = 0;
		for (Field field : fields) {
			if (field.isAnnotationPresent(Column.class)) {
				Column column = field.getAnnotation(Column.class);
				sqlReturn += column.name() + ",";
				count++;
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

	private String createSQLFindAll() {
		String sqlReturn = "select * from ";
		String tableName = "";
		if (this.zClass.isAnnotationPresent(Table.class)) {
			Table table = this.zClass.getAnnotation(Table.class);
			tableName = table.name();
		}
		sqlReturn += tableName;
		return sqlReturn;
	}

	private StringBuilder createSQLFindByCondition(Map<String, Object> condition) {
		String tableName = "";
		if (this.zClass.isAnnotationPresent(Table.class)) {
			Table table = this.zClass.getAnnotation(Table.class);
			tableName = table.name();
		}
		StringBuilder result = new StringBuilder("select * from " + tableName + " where 1 = 1 ");
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
		return result;
	}

}
