package com.group.libraryapp.temp;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PersonService {
    private final AddressRepository addressRepository;
    private final PersonRepository personRepository;

    public PersonService(AddressRepository addressRepository, PersonRepository personRepository) {
        this.addressRepository = addressRepository;
        this.personRepository = personRepository;
    }

    @Transactional
    public void savePerson() {
        Person person = personRepository.save(new Person());
        Address address = addressRepository.save(new Address());
        person.setAddress(address);


        //address.getPerson();

        //address.setPerson(person);
        //연관 관계의 주인 효과
        //상대 테이블을 참조하고 있으면 연관 관계의 주인
        //주인이 아니면 mappeedBy 사용
        //setter 사용되어야만 테이블 연결
        //객체가 연결되는 기준이 된다.

        //personRepository.save(person);
    }
}
