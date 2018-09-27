package com.example.easynotes;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import com.app.controllers.NoteController;
import com.app.easynotes.dto.Note;
import com.app.repositories.NoteRepository;

/*@RunWith(SpringRunner.class)
@SpringBootTest(classes = EasyNotesApplication.class)
@WebAppConfiguration*/
public class IntegrationNoteControllerTest {

	/*MockMvc mockMvc;

	@Autowired
	NoteController controller;

	@Autowired
	NoteRepository repo;

	 @Autowired
	 public NoteRepository mockMyRepository() {

	 NoteRepository repo = Mockito.mock(NoteRepository.class);
	 Mockito.when(repo.findAll()).thenReturn(new ArrayList<Note>());

	 return repo;
	 }

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setup() throws Exception {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void test_getAllNotes_list() throws Exception {
		ResultActions andExpect = mockMvc.perform(get("/api/notes")).andExpect(status().isOk());
	}*/
}
