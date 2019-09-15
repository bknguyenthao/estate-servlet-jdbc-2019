package com.laptrinhjavaweb.controller.admin;

import java.awt.print.Pageable;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.paging.Pageble;
import com.laptrinhjavaweb.service.BuildingService;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.utils.HandlerSubmit;

@WebServlet(urlPatterns = { "/admin-building" })
public class BuildingController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	IBuildingService buildingService;

	public BuildingController() {
		if (buildingService != null) {
			buildingService = new BuildingService();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BuildingDTO model = HandlerSubmit.readDataSubmit(BuildingDTO.class, request);
		String action = request.getParameter("action");
		String url = "";
		if (action.equals("LIST")) {
			url = "/views/list.jsp";
			BuildingSearchBuilder buildingSearchBuilder = initBuildingBuilder(model);
			Pageble pageble = new PageRequest(null, null, null);
			model.setListResult(buildingService.findAll(buildingSearchBuilder, pageble));
		} else if (action.equals("EDIT")) {
			url = "/views/edit.jsp";
		}
		request.setAttribute("model", model);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	private BuildingSearchBuilder initBuildingBuilder(BuildingDTO model) {
		BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder().setName(model.getName())
				.setNumberOfBasement(model.getNumberOfBasement()).setWard(model.getWard()).setStreet(model.getStreet())
				.setAreaFrom(model.getAreaFrom()).setAreaTo(model.getAreaTo()).setCostRentFrom(model.getCostRentFrom())
				.setCostRentTo(model.getCostRentTo()).build();

		return buildingSearchBuilder;
	}
}
