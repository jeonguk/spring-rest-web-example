package com.jeonguk.web.controller;

import com.jeonguk.web.config.annotation.ApiVersion;
import com.jeonguk.web.dto.PersonDTO;
import com.jeonguk.web.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/person")
@ApiVersion
public class PersonController {

	private PersonService personService;

	@Autowired
	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@GetMapping("/{id}")
	PersonDTO.ResPerson getPerson(@PathVariable Long id) {
		return personService.getPerson(id);
	}

	@PostMapping
	PersonDTO.ResPerson savePerson(@RequestBody PersonDTO.ReqPerson newPerson) {
		return personService.savePerson(newPerson);
	}
}
