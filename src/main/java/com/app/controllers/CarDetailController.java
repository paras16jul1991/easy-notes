package com.app.controllers;

import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.car.dto.Car;
import com.app.car.dto.CarDTO;
import com.app.repositories.CarRepository;

@RestController
@RequestMapping("/api/")
public class CarDetailController {

	@Autowired
	CarRepository repository;

	@GetMapping(value = ("/cool-cars"), params = "version=v1", produces = { "application/xml", "application/json" })
	public List<Car> coolCars() throws Exception {
		Specification<Car> spc = new Specification<Car>() {
			public Predicate toPredicate(Root<Car> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.lessThan(root.get("car_id"), "2");
			}
		};
		Optional<Car> car = Optional.of(new Car());
		car.orElseThrow(() -> new Exception("Hello"));
		return repository.findAll(spc);
	}

	@GetMapping("/cool-cars/count")
	public List<CarDTO> coolCarsCount() throws Exception {

		return repository.findAllCount();
	}

	@GetMapping("/cool-cars/unique")
	public List<Car> findDistinctByName() throws Exception {

		return repository.findDistinctByName();
	}
}
