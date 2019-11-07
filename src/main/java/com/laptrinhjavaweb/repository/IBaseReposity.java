package com.laptrinhjavaweb.repository;

import java.util.List;
import java.util.Map;

public interface IBaseReposity<T> {

	Long insert(Object object);

	void update(Object object);

	void delete(Long id);

	T findById(Long id);

	List<T> findAll();

	List<T> findByCondition(Map<String, Object> condition);
}
