package com.laptrinhjavaweb.repository;

import java.util.List;

public interface GenericJDBC<T> {

	List<T> selectAnnotationWay(String sql, Object... objects);

	List<T> selectCustomWay(String sql, Object... objects);
	// insert by old way
	// Long insert(String sql, Object... objects);

//	void updateOrDelete(String sql, Object... objects);

	Long insert(Object object);

	void update(Object object);

	void delete(Long id);

	T findById(Long id);

	List<T> findAll(Object... objects);
}
