package com.app.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.quote.dto.Quote;
import com.app.quote.dto.Quotes;
import com.app.repositories.QuoteRepository;

@RestController
@RequestMapping("/api/quotes/")
public class QuoteController {

	@Autowired
	QuoteRepository quoteRepository;

	@GetMapping(value = "/{username}", produces = { "application/xml", "application/json" })
	List<String> getQuotesByuserName(@PathVariable(name = "username") String userName) {
		return getQuotes(userName);
	}

	@PostMapping("/add")
	public List<String> add(@RequestBody final Quotes quotes) {
		quotes.getQuotes().stream().forEach(q -> quoteRepository.save(new Quote(q, quotes.getUsername())));
		return getQuotes(quotes.getUsername());
	}

	@CacheEvict(value = "quotes", allEntries = true)
	@DeleteMapping("/delete/{username}")
	public boolean delete(@PathVariable(name = "username") String username) {

		List<Quote> quotes = quoteRepository.findByusername(username);
		quoteRepository.deleteAll(quotes);

		return true;
	}

	private List<String> getQuotes(String userName) {
		return quoteRepository.findByusername(userName).stream().map(q -> q.getQuote()).collect(Collectors.toList());
	}
}
