package com.app.car.dto;

public class CarDTO {

	public long count;
	public int car_id;

	public CarDTO() {
	}

	public CarDTO(long count, int car_id) {
		this.count = count;
		this.car_id = car_id;
	}

	public long getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCar_id() {
		return car_id;
	}

	public void setCar_id(int car_id) {
		this.car_id = car_id;
	}
}
