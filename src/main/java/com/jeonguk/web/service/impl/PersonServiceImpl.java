package com.jeonguk.web.service.impl;

import com.jeonguk.web.dto.PersonDTO;
import com.jeonguk.web.entity.Person;
import com.jeonguk.web.exception.NotFoundException;
import com.jeonguk.web.repository.PersonRepository;
import com.jeonguk.web.service.PersonService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Slf4j
@AllArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {

	private final ModelMapper modelMapper;
	private final PersonRepository personRepository;

	@Override
	public PersonDTO.ResPerson getPerson(Long id) {
		final Person p = personRepository.findById(id).orElseThrow(() -> new NotFoundException("Cannot find person info"));
		return modelMapper.map(p,PersonDTO.ResPerson.class);
	}

	@Override
	@Transactional
	public PersonDTO.ResPerson savePerson(PersonDTO.ReqPerson savePerson) {
		Person person = new Person();
		person.setPersonName(savePerson.getPersonName());
		return modelMapper.map(personRepository.save(person),PersonDTO.ResPerson.class);
	}

	@Override
	@HystrixCommand(fallbackMethod = "defaultGreeting")
	public String getGreeting(String username) {
		return new RestTemplate()
			.getForObject("http://test.com:6379/greeting/{username}",
				String.class, username);
	}

	private String defaultGreeting(String username) {
		return "Hello User!";
	}
}
