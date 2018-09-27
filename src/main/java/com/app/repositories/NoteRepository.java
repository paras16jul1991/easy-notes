package com.app.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.easynotes.dto.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

	@Cacheable("Notes")
	public List<Note> findAllByTitle(String args);
	
	@Query("from Note note join fetch note.addresses Addresses")
	public List<Note> findAll();
	
	public Note getById(Long id);

	@Transactional
	public long deleteByTitle(String args);

	public long countByTitle(String title);

}
