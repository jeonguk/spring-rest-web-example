package com.jeonguk.web.controller;

import com.jeonguk.web.dto.PersonDTO;
import com.jeonguk.web.entity.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.jeonguk.web.config.Constants.VERSION;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
public class PersonControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private PersonController personController;

	@Test
	public void getPerson_Test() throws Exception {

		Person savePerson = createPerson();
		PersonDTO.ResPerson person = new PersonDTO.ResPerson();
		person.setId(savePerson.getId());
		person.setPersonName(savePerson.getPersonName());
		person.setCreatedAt(savePerson.getCreatedAt());
		given(personController.getPerson(person.getId())).willReturn(person);

		mvc.perform(get(VERSION + "/person/1")
				.contentType(APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("personName", is(person.getPersonName())));
	}


	private Person createPerson() {
		Person person = new Person();
		person.setPersonName("jeonguk");
		return person;
	}
}
