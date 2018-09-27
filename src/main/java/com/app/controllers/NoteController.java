package com.app.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.easynotes.dto.Note;
import com.app.easynotes.exception.ResourceNotFoundException;
import com.app.repositories.NoteRepository;

@RestController
@RequestMapping("/api/")
public class NoteController {
	@Autowired
	NoteRepository noteRepository;

	@GetMapping("/notes")
	public List<Note> getAllNotes() {
		return noteRepository.findAll();
	}

	@PostMapping("/notes")
	public ResponseEntity<String> createNote(@Valid @RequestBody Note note, Errors errors) {

		if (errors.hasErrors()) {
			return new ResponseEntity(new Exception(errors.getFieldError().getDefaultMessage()),
					HttpStatus.BAD_REQUEST);
		}
		noteRepository.save(note);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/notes/{id}")
	public Note getNoteById(@PathVariable(value = "id") Long noteId) {
		return noteRepository.findById(noteId).orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
	}

	@PutMapping("/notes/{id}")
	public Note updateNote(@PathVariable(value = "id") Long noteId, @Valid @RequestBody Note noteDetails) {

		Note note = noteRepository.findById(noteId)
				.orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

		note.setTitle(noteDetails.getTitle());
		note.setContent(noteDetails.getContent());

		Note updatedNote = noteRepository.save(note);
		return updatedNote;
	}

	@DeleteMapping("/notes/{id}")
	public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {
		Note note = noteRepository.findById(noteId)
				.orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

		noteRepository.delete(note);

		return ResponseEntity.ok().build();
	}

	@GetMapping("/notes/getByTitle/{title}")
	public List<Note> getTitleByName(@PathVariable(value = "title") String title) {
		return noteRepository.findAllByTitle(title);
	}

	@GetMapping("/notes/getById/{id}")
	public Note getById(@PathVariable(value = "id") Long id) {
		return noteRepository.getById(id);
	}

	@DeleteMapping("/notes/deleteByTitle/{title}")
	public long deleteByTitle(@PathVariable(value = "title") String title) {
		return noteRepository.deleteByTitle(title);
	}

	@GetMapping("/notes/countByTitle/{title}")
	public long countByTitle(@PathVariable(value = "title") String title) {
		return noteRepository.countByTitle(title);
	}

}
