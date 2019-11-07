package com.laptrinhjavaweb.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.utils.Static;

@WebServlet(urlPatterns = { "/api-admin-building" })
public class BuildingAPI extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Inject
	private IBuildingService buildingService;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		// get string from request type JSON then convert to DTO object
		BuildingDTO buildingDTO = objectMapper.readValue(Static.getStringFromRequestJSON(request), BuildingDTO.class);

		Long id = buildingService.insert(buildingDTO);
		buildingDTO.setId(id);
		objectMapper.writeValue(response.getOutputStream(), buildingDTO);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		// get string from request type JSON then convert to DTO object
		BuildingDTO buildingDTO = objectMapper.readValue(Static.getStringFromRequestJSON(request), BuildingDTO.class);

		buildingService.update(buildingDTO);
		objectMapper.writeValue(response.getOutputStream(), "update success");
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");

		String idStr = request.getParameter("id");
		if (idStr != null) {
			Long id = Long.parseLong(idStr);
			buildingService.delete(id);
			objectMapper.writeValue(response.getOutputStream(), "delete success");
		} else {
			objectMapper.writeValue(response.getOutputStream(), "id null");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
//		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");

		String idStr = request.getParameter("id");
		if (idStr != null) {
			Long id = Long.parseLong(idStr);
			BuildingDTO buildingDTO = buildingService.findById(id);
			objectMapper.writeValue(response.getOutputStream(), buildingDTO);
		} else {
			objectMapper.writeValue(response.getOutputStream(), "id null");
		}
	}
}
