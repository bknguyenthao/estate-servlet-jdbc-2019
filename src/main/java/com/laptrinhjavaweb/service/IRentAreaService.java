package com.laptrinhjavaweb.service;

import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.dto.RentAreaDTO;

public interface IRentAreaService {
	Long insert(RentAreaDTO rentAreaDTO);

	void update(RentAreaDTO rentAreaDTO);

	void delete(Long id);

	RentAreaDTO findById(Long id);

	List<RentAreaDTO> findAll();

	List<RentAreaDTO> findByCondition(Map<String, Object> condition);
}
