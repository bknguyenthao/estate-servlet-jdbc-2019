package com.laptrinhjavaweb.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.utils.Static;

@WebServlet(urlPatterns = { "/admin-building" })
public class BuildingController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private IBuildingService buildingService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String url = "";
		if (action.equals("LIST")) {
			url = "/views/list.jsp";

			BuildingDTO buildingSearch = Static.readDataFromRequest(BuildingDTO.class, request);

			BuildingSearchBuilder buildingSearchBuilder = initBuildingSearchBuilder(buildingSearch);
			Pageable pageble = new PageRequest(null, null, null);
			request.setAttribute("buildings", buildingService.searchBuilding(buildingSearchBuilder, pageble));
			request.setAttribute("buildingsearch", buildingSearch);
		} else if (action.equals("EDIT")) {
			url = "/views/edit.jsp";
			Long id = Long.parseLong(request.getParameter("buildingId"));
			BuildingDTO buildingEdit = buildingService.findById(id);
			request.setAttribute("buildingedit", buildingEdit);
		} else if (action.equals("ADD")) {
			url = "/views/add.jsp";
		} else if (action.equals("DELETE")) {
			Long id = Long.parseLong(request.getParameter("buildingId"));
			buildingService.delete(id);

			url = "/views/list.jsp";
			request.setAttribute("buildings", buildingService.findAll());
		}
		request.setAttribute("districts", Static.getDistrict());
		request.setAttribute("buildingtypes", Static.getBuildingType());

		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

	private BuildingSearchBuilder initBuildingSearchBuilder(BuildingDTO model) {
		BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder().setName(model.getName())
				.setDistrict(model.getDistrict()).setWard(model.getWard()).setStreet(model.getStreet())
				.setBuildingArea(model.getBuildingArea()).setNumberOfBasement(model.getNumberOfBasement())
				.setDirection(model.getDirection()).setLevel(model.getLevel()).setAreaRentFrom(model.getAreaRentFrom())
				.setAreaRentTo(model.getAreaRentTo()).setCostRentFrom(model.getCostRentFrom())
				.setCostRentTo(model.getCostRentTo()).setManagerName(model.getManagerName())
				.setManagerPhone(model.getManagerPhone()).setBuildingTypes(model.getBuildingTypes()).build();

		return buildingSearchBuilder;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
