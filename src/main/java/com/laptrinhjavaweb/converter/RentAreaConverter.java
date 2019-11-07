package com.laptrinhjavaweb.converter;

import org.modelmapper.ModelMapper;

import com.laptrinhjavaweb.dto.RentAreaDTO;
import com.laptrinhjavaweb.entity.RentAreaEntity;

public class RentAreaConverter {
	public RentAreaDTO convertToDTO(RentAreaEntity rentAreaEntity) {
		ModelMapper modelMapper = new ModelMapper();
		RentAreaDTO result = modelMapper.map(rentAreaEntity, RentAreaDTO.class);
		return result;
	}

	public RentAreaEntity convertToEntity(RentAreaDTO rentAreaDTO) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(rentAreaDTO, RentAreaEntity.class);
	}
}
