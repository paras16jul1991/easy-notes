package com.app.repositories;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.app.user.dto.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer>, JpaSpecificationExecutor<User> {

	@Cacheable("users")
	public Page<User> findAll(Pageable pageable);

	public List<User> findAll();
	/*
	 * @Query("select p from User p, Address a where p.id=uid and a.type like ?1"
	 * ) public List<User> findByAddressType(String type, Pageable pageable);
	 */
}
