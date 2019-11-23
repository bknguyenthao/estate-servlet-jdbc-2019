package com.laptrinhjavaweb.builder;

public class BuildingSearchBuilder {
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
	private String overtimeCost;
	private String electriccityCost;
	private String deposit;
	private String payment;
	private String timeRent;
	private String timeDecorator;
	private String managerName;
	private String managerPhone;
	private String buildingType;
	private String direction;
	private String level;

	private String costRentFrom;
	private String costRentTo;
	private String areaRentFrom;
	private String areaRentTo;
	private String[] buildingTypes;

	public String getName() {
		return name;
	}

	public String getNumberOfBasement() {
		return numberOfBasement;
	}

	public String getBuildingArea() {
		return buildingArea;
	}

	public String getDistrict() {
		return district;
	}

	public String getWard() {
		return ward;
	}

	public String getStreet() {
		return street;
	}

	public String getStructure() {
		return structure;
	}

	public String getCostRent() {
		return costRent;
	}

	public String getCostDescription() {
		return costDescription;
	}

	public String getServiceCost() {
		return serviceCost;
	}

	public String getCarCost() {
		return carCost;
	}

	public String getMotorbikeCost() {
		return motorbikeCost;
	}

	public String getOvertimeCost() {
		return overtimeCost;
	}

	public String getElectriccityCost() {
		return electriccityCost;
	}

	public String getDeposit() {
		return deposit;
	}

	public String getPayment() {
		return payment;
	}

	public String getTimeRent() {
		return timeRent;
	}

	public String getTimeDecorator() {
		return timeDecorator;
	}

	public String getManagerName() {
		return managerName;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public String getBuildingType() {
		return buildingType;
	}

	public String getDirection() {
		return direction;
	}

	public String getLevel() {
		return level;
	}

	public String getCostRentFrom() {
		return costRentFrom;
	}

	public String getCostRentTo() {
		return costRentTo;
	}

	public String getAreaRentFrom() {
		return areaRentFrom;
	}

	public String getAreaRentTo() {
		return areaRentTo;
	}

	public String[] getBuildingTypes() {
		return buildingTypes;
	}

	public BuildingSearchBuilder(Builder builder) {
		this.name = builder.name;
		this.numberOfBasement = builder.numberOfBasement;
		this.buildingArea = builder.buildingArea;
		this.district = builder.district;
		this.ward = builder.ward;
		this.street = builder.street;
		this.structure = builder.structure;
		this.costRent = builder.costRent;
		this.costDescription = builder.costDescription;
		this.serviceCost = builder.serviceCost;
		this.carCost = builder.carCost;
		this.motorbikeCost = builder.motorbikeCost;
		this.overtimeCost = builder.overtimeCost;
		this.electriccityCost = builder.electriccityCost;
		this.deposit = builder.deposit;
		this.payment = builder.payment;
		this.timeRent = builder.timeRent;
		this.timeDecorator = builder.timeDecorator;
		this.managerName = builder.managerName;
		this.managerPhone = builder.managerPhone;
		this.buildingType = builder.buildingType;
		this.direction = builder.direction;
		this.level = builder.level;

		this.costRentFrom = builder.costRentFrom;
		this.costRentTo = builder.costRentTo;
		this.areaRentFrom = builder.areaRentFrom;
		this.areaRentTo = builder.areaRentTo;
		this.buildingTypes = builder.buildingTypes;
	}

	public static class Builder {
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
		private String overtimeCost;
		private String electriccityCost;
		private String deposit;
		private String payment;
		private String timeRent;
		private String timeDecorator;
		private String managerName;
		private String managerPhone;
		private String buildingType;
		private String direction;
		private String level;

		private String costRentFrom;
		private String costRentTo;
		private String areaRentFrom;
		private String areaRentTo;
		private String[] buildingTypes;

		public BuildingSearchBuilder build() {
			return new BuildingSearchBuilder(this);
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setNumberOfBasement(String numberOfBasement) {
			this.numberOfBasement = numberOfBasement;
			return this;
		}

		public Builder setBuildingArea(String buildingArea) {
			this.buildingArea = buildingArea;
			return this;
		}

		public Builder setDistrict(String district) {
			this.district = district;
			return this;
		}

		public Builder setWard(String ward) {
			this.ward = ward;
			return this;
		}

		public Builder setStreet(String street) {
			this.street = street;
			return this;
		}

		public Builder setStructure(String structure) {
			this.structure = structure;
			return this;
		}

		public Builder setCostRent(String costRent) {
			this.costRent = costRent;
			return this;
		}

		public Builder setCostDescription(String costDescription) {
			this.costDescription = costDescription;
			return this;
		}

		public Builder setServiceCost(String serviceCost) {
			this.serviceCost = serviceCost;
			return this;
		}

		public Builder setCarCost(String carCost) {
			this.carCost = carCost;
			return this;
		}

		public Builder setMotorbikeCost(String motorbikeCost) {
			this.motorbikeCost = motorbikeCost;
			return this;
		}

		public Builder setOvertimeCost(String overtimeCost) {
			this.overtimeCost = overtimeCost;
			return this;
		}

		public Builder setElectriccityCost(String electriccityCost) {
			this.electriccityCost = electriccityCost;
			return this;
		}

		public Builder setDeposit(String deposit) {
			this.deposit = deposit;
			return this;
		}

		public Builder setPayment(String payment) {
			this.payment = payment;
			return this;
		}

		public Builder setTimeRent(String timeRent) {
			this.timeRent = timeRent;
			return this;
		}

		public Builder setTimeDecorator(String timeDecorator) {
			this.timeDecorator = timeDecorator;
			return this;
		}

		public Builder setManagerName(String managerName) {
			this.managerName = managerName;
			return this;
		}

		public Builder setManagerPhone(String managerPhone) {
			this.managerPhone = managerPhone;
			return this;
		}

		public Builder setBuildingType(String buildingType) {
			this.buildingType = buildingType;
			return this;
		}

		public Builder setDirection(String direction) {
			this.direction = direction;
			return this;
		}

		public Builder setLevel(String level) {
			this.level = level;
			return this;
		}

		public Builder setCostRentFrom(String costRentFrom) {
			this.costRentFrom = costRentFrom;
			return this;
		}

		public Builder setCostRentTo(String costRentTo) {
			this.costRentTo = costRentTo;
			return this;
		}

		public Builder setAreaRentFrom(String areaRentFrom) {
			this.areaRentFrom = areaRentFrom;
			return this;
		}

		public Builder setAreaRentTo(String areaRentTo) {
			this.areaRentTo = areaRentTo;
			return this;
		}

		public Builder setBuildingTypes(String[] buildingTypes) {
			this.buildingTypes = buildingTypes;
			return this;
		}
	}
}
