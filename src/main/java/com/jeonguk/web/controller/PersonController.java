package com.jeonguk.web.controller;

import com.jeonguk.web.config.annotation.ApiVersion;
import com.jeonguk.web.dto.PersonDTO;
import com.jeonguk.web.service.PersonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/person")
@ApiVersion
public class PersonController {

	final private PersonService personService;

	@GetMapping("/{id}")
	PersonDTO.ResPerson getPerson(@PathVariable Long id) {
		return personService.getPerson(id);
	}

	@PostMapping
	PersonDTO.ResPerson savePerson(@RequestBody PersonDTO.ReqPerson newPerson) {
		return personService.savePerson(newPerson);
	}
	
}
