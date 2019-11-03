package com.laptrinhjavaweb.builder;

public class BuildingSearchBuilder {
	private String name;
	private String ward;
	private String street;
	private String numberOfBasement;
	private String costRentFrom;
	private String costRentTo;
	private String areaFrom;
	private String areaTo;
	private String[] buildingTypes;

	public String getName() {
		return name;
	}

	public String getWard() {
		return ward;
	}

	public String getStreet() {
		return street;
	}

	
	public String getCostRentFrom() {
		return costRentFrom;
	}

	public String getCostRentTo() {
		return costRentTo;
	}

	public String getAreaFrom() {
		return areaFrom;
	}

	public String getAreaTo() {
		return areaTo;
	}

	
	public String getNumberOfBasement() {
		return numberOfBasement;
	}

	public String[] getBuildingTypes() {
		return buildingTypes;
	}


	
	public BuildingSearchBuilder(Builder builder) {
		this.name = builder.name;
		this.ward = builder.ward;
		this.street = builder.street;
		this.numberOfBasement = builder.numberOfBasement;
		this.costRentFrom = builder.costRentFrom;
		this.costRentTo = builder.costRentTo;
		this.areaFrom = builder.areaFrom;
		this.areaTo = builder.areaTo;
		this.buildingTypes = builder.buildingTypes;
	}


	public static class Builder {
		private String name;
		private String ward;
		private String street;
		private String numberOfBasement;
		private String costRentFrom;
		private String costRentTo;
		private String areaFrom;
		private String areaTo;
		private String[] buildingTypes;

		public Builder setName(String name) {
			this.name = name;
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

		
		public Builder setCostRentFrom(String costRentFrom) {
			this.costRentFrom = costRentFrom;
			return this;
		}

		public Builder setCostRentTo(String costRentTo) {
			this.costRentTo = costRentTo;
			return this;
		}

		public Builder setAreaFrom(String areaFrom) {
			this.areaFrom = areaFrom;
			return this;
		}

		public Builder setAreaTo(String areaTo) {
			this.areaTo = areaTo;
			return this;
		}

		
		public Builder setNumberOfBasement(String numberOfBasement) {
			this.numberOfBasement = numberOfBasement;
			return this;
		}

		public Builder setBuildingTypes(String[] buildingTypes) {
			this.buildingTypes = buildingTypes;
			return this;
		}

		public BuildingSearchBuilder build() {
			return new BuildingSearchBuilder(this);
		}

	}
}
