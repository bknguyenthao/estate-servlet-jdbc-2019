package com.laptrinhjavaweb.api;

import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.service.BuildingService;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.utils.HandlerJSON;

@WebServlet(urlPatterns = { "/api-admin-building" })
public class BuildingAPI extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private IBuildingService buildingService;

	public BuildingAPI() {
		buildingService = new BuildingService();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		// BuildingDTO buildingDTO =
		// HandlerJSON.of(request.getReader()).toModel(BuildingDTO.class);
		// get JSON from request then convert to DTO object
		BuildingDTO buildingDTO = objectMapper.readValue(HandlerJSON.getJSON(request), BuildingDTO.class);

		buildingDTO = buildingService.save(buildingDTO);
		objectMapper.writeValue(response.getOutputStream(), buildingDTO);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		// BuildingDTO buildingDTO =
		// HandlerJSON.of(request.getReader()).toModel(BuildingDTO.class);
		// get JSON from request then convert to DTO object
		BuildingDTO buildingDTO = objectMapper.readValue(HandlerJSON.getJSON(request), BuildingDTO.class);

		buildingService.update(buildingDTO);
		objectMapper.writeValue(response.getOutputStream(), "update success");
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		// BuildingDTO buildingDTO =
		// HandlerJSON.of(request.getReader()).toModel(BuildingDTO.class);
		// get JSON from request then convert to DTO object
		BuildingDTO buildingDTO = objectMapper.readValue(HandlerJSON.getJSON(request), BuildingDTO.class);

		buildingService.delete(buildingDTO);
		objectMapper.writeValue(response.getOutputStream(), "delete success");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
//		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");

		String idStr = request.getParameter("id");
		if (idStr != null) {
			Long id = Long.parseLong(request.getParameter("id"));
			BuildingDTO buildingDTO = buildingService.findById(id);
			objectMapper.writeValue(response.getOutputStream(), buildingDTO);
		} else {
			List<BuildingDTO> listBuildingDTO = buildingService.findAll();
			objectMapper.writeValue(response.getOutputStream(), listBuildingDTO);
		}
	}
}
