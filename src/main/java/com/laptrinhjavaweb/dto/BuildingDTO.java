package com.laptrinhjavaweb.dto;

public class BuildingDTO extends BaseDTO<BuildingDTO> {
	private String name;
	private String numberOfBasement;
	private String buildingArea;
	private String district;
	private String ward;
	private String street;
	private String structure;
	private String costRent;
	private String costDescription;
	private String serviceCost;
	private String carCost;
	private String motorbikeCost;
	private String overtimecCost;
	private String electriccityCost;
	private String deposit;
	private String payment;
	private String timerent;
	private String timeDecorator;
	private String managerName;
	private String managerPhone;
	private String buildingType;
	private String direction;
	private String level;
	private String costRentFrom;
	private String costRentTo;
	private String areaFrom;
	private String areaTo;
	private String[] buildingTypes;
	private String address;
	private String rentArea;

	public String getAddress() {
		this.address = this.ward + "," + this.street;
		return address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumberOfBasement() {
		return numberOfBasement;
	}

	public void setNumberOfBasement(String numberOfBasement) {
		this.numberOfBasement = numberOfBasement;
	}

	public String getBuildingArea() {
		return buildingArea;
	}

	public void setBuildingArea(String buildingArea) {
		this.buildingArea = buildingArea;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStructure() {
		return structure;
	}

	public void setStructure(String structure) {
		this.structure = structure;
	}

	public String getCostDescription() {
		return costDescription;
	}

	public void setCostDescription(String costDescription) {
		this.costDescription = costDescription;
	}

	public String getServiceCost() {
		return serviceCost;
	}

	public void setServiceCost(String serviceCost) {
		this.serviceCost = serviceCost;
	}

	public String getCarCost() {
		return carCost;
	}

	public void setCarCost(String carCost) {
		this.carCost = carCost;
	}

	public String getMotorbikeCost() {
		return motorbikeCost;
	}

	public void setMotorbikeCost(String motorbikeCost) {
		this.motorbikeCost = motorbikeCost;
	}

	public String getOvertimecCost() {
		return overtimecCost;
	}

	public void setOvertimecCost(String overtimecCost) {
		this.overtimecCost = overtimecCost;
	}

	public String getElectriccityCost() {
		return electriccityCost;
	}

	public void setElectriccityCost(String electriccityCost) {
		this.electriccityCost = electriccityCost;
	}

	public String getDeposit() {
		return deposit;
	}

	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getTimerent() {
		return timerent;
	}

	public void setTimerent(String timerent) {
		this.timerent = timerent;
	}

	public String getTimeDecorator() {
		return timeDecorator;
	}

	public void setTimeDecorator(String timeDecorator) {
		this.timeDecorator = timeDecorator;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}

	public String getBuildingType() {
		return buildingType;
	}

	public void setBuildingType(String buildingType) {
		this.buildingType = buildingType;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getCostRent() {
		return costRent;
	}

	public void setCostRent(String costRent) {
		this.costRent = costRent;
	}

	public String getCostRentFrom() {
		return costRentFrom;
	}

	public void setCostRentFrom(String costRentFrom) {
		this.costRentFrom = costRentFrom;
	}

	public String getCostRentTo() {
		return costRentTo;
	}

	public void setCostRentTo(String costRentTo) {
		this.costRentTo = costRentTo;
	}

	public String getAreaFrom() {
		return areaFrom;
	}

	public void setAreaFrom(String areaFrom) {
		this.areaFrom = areaFrom;
	}

	public String getAreaTo() {
		return areaTo;
	}

	public void setAreaTo(String areaTo) {
		this.areaTo = areaTo;
	}

	public String[] getBuildingTypes() {
		return buildingTypes;
	}

	public void setBuildingTypes(String[] buildingTypes) {
		this.buildingTypes = buildingTypes;
	}

	public String getRentArea() {
		return rentArea;
	}

	public void setRentArea(String rentArea) {
		this.rentArea = rentArea;
	}

}
