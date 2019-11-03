package com.laptrinhjavaweb.controller.admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
import com.laptrinhjavaweb.paging.Pageble;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.utils.HandlerSubmit;

@WebServlet(urlPatterns = { "/admin-building" })
public class BuildingController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private IBuildingService buildingService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BuildingDTO model = HandlerSubmit.readDataSubmit(BuildingDTO.class, request);
		String action = request.getParameter("action");
		String url = "";
		if (action.equals("LIST")) {
			url = "/views/list.jsp";
			BuildingSearchBuilder buildingSearchBuilder = initBuildingSearchBuilder(model);
			Pageble pageble = new PageRequest(null, null, null);
			model.setListResult(buildingService.findAll(buildingSearchBuilder, pageble));
		} else if (action.equals("EDIT")) {
			url = "/views/edit.jsp";
		}
		request.setAttribute("districts", getDistrict());
		request.setAttribute("buildingtypes", getBuildingType());
		request.setAttribute("model", model);
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

	private Map<String, String> getDistrict() {
		Map<String, String> result = new HashMap<String, String>();
		result.put("QUAN_1", "Quận 1");
		result.put("QUAN_2", "Quận 2");
		result.put("QUAN_3", "Quận 3");
		return result;
	}

	private Map<String, String> getBuildingType() {
		Map<String, String> result = new HashMap<String, String>();
		result.put("TANG_TRET", "Tầng trệt");
		result.put("NGUYEN_CAN", "Nguyên căn");
		result.put("NOI_THAT", "Nội thất");
		return result;
	}
}
