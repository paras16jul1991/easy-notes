package com.app.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.repositories.UserRepository;
import com.app.user.dto.User;

@RestController
@RequestMapping("/api/")
public class UserController {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	UserRepository userRepo;

	@GetMapping("/users/")
	public List<User> getUser() {
		return userRepo.findAll();
	}

	@Cacheable("users")
	@GetMapping("/users/all")
	public Page<User> getUser(@RequestParam("page") int page, @RequestParam("size") int size,
			@RequestParam("sortBy") String sortBy) {
		List<Sort.Order> orders = new ArrayList<>();
		orders.add(new Sort.Order(sortBy));
		Sort.Direction direction = ("ASC") == Sort.Direction.ASC.toString() ? Sort.Direction.ASC : Sort.Direction.DESC;
		orders.add(new Sort.Order(direction, sortBy));
		Pageable pageable = new PageRequest(page, size, orders.isEmpty() ? null : new Sort(orders));
		return userRepo.findAll(pageable);
	}

}
