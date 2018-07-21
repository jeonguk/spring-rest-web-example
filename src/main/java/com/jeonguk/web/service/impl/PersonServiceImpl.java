package com.jeonguk.web.service.impl;

import com.jeonguk.web.dto.PersonDTO;
import com.jeonguk.web.entity.Person;
import com.jeonguk.web.exception.NotFoundException;
import com.jeonguk.web.repository.PersonRepository;
import com.jeonguk.web.service.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonServiceImpl implements PersonService {

	private ModelMapper modelMapper;
	private PersonRepository personRepository;

	@Autowired
	public PersonServiceImpl(ModelMapper modelMapper, PersonRepository personRepository) {
		this.modelMapper = modelMapper;
		this.personRepository = personRepository;
	}

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
}
