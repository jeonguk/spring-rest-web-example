package com.jeonguk.web.service;

import com.jeonguk.web.dto.PersonDTO;

public interface PersonService {
	PersonDTO.ResPerson getPerson(Long id);
	PersonDTO.ResPerson savePerson(PersonDTO.ReqPerson person);
	String getGreeting(String username);
}
