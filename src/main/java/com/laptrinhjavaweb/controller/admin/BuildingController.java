package com.laptrinhjavaweb.controller.admin;

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
		BuildingDTO buildingSearch = Static.readDataFromRequest(BuildingDTO.class, request);
		String action = request.getParameter("action");
		String url = "";
		if (action.equals("LIST")) {
			url = "/views/list.jsp";
			BuildingSearchBuilder buildingSearchBuilder = initBuildingSearchBuilder(buildingSearch);
			Pageable pageble = new PageRequest(null, null, null);
			request.setAttribute("buildings", buildingService.searchBuilding(buildingSearchBuilder, pageble));
		} else if (action.equals("EDIT")) {
			url = "/views/edit.jsp";
		}
		request.setAttribute("districts", Static.getDistrict());
		request.setAttribute("buildingtypes", Static.getBuildingType());
		request.setAttribute("buildingsearch", buildingSearch);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

	private BuildingSearchBuilder initBuildingSearchBuilder(BuildingDTO model) {
		BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder().setName(model.getName())
				.setWard(model.getWard()).setAreaFrom(model.getAreaFrom()).setAreaTo(model.getAreaTo())
				.setCostRentFrom(model.getCostRentFrom()).setCostRentTo(model.getCostRentTo())
				.setBuildingTypes(model.getBuildingTypes()).build();

		return buildingSearchBuilder;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
