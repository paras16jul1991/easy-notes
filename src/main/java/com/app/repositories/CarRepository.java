package com.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.car.dto.Car;
import com.app.car.dto.CarDTO;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer>, JpaSpecificationExecutor<Car> {

	@Query("select new com.app.car.dto.CarDTO(count(car.car_id),car.car_id) from Car car group by car.car_id")
	List<CarDTO> findAllCount();

	@Query("select car from Car car group by car.name,car.id")
	List<Car> findDistinctByName();

}
