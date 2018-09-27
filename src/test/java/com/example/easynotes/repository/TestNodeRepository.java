package com.example.easynotes.repository;

import static org.junit.Assert.assertEquals;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.easynotes.dto.Note;
import com.app.repositories.NoteRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class TestNodeRepository {
	@Autowired
	TestEntityManager manager;

	@Autowired
	NoteRepository noteRepository;

	@Test
	public void test_all_test() {

		Note note1 = new Note();
		note1.setTitle("paras2");
		note1.setContent("c1");

		Note note3 = new Note();
		note3.setTitle("paras1");
		note3.setContent("c2");

		manager.persist(note1);
		manager.flush();

		manager.persist(note3);
		manager.flush();

		List<Note> findAll = noteRepository.findAll();
		assertEquals(4, findAll.size());
	}
}
