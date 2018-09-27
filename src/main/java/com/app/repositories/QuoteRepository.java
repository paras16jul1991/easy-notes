package com.app.repositories;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.quote.dto.Quote;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Integer> {

	@Cacheable("quotes")
	public List<Quote> findByusername(String username);

}
