package com.laptrinhjavaweb.utils;

import org.modelmapper.ModelMapper;

import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.UserEntity;

//convert UserDTO <-> UserEntity
public class UserConverter {
	public UserDTO convertToDTO(UserEntity userEntity) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(userEntity, UserDTO.class);
	}

	public UserEntity convertToEntity(UserDTO userDTO) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(userDTO, UserEntity.class);
	}
}
