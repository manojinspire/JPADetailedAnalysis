package com.JPACrudDemo.jpacrud.oneToone.Unidirectional;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JpacrudService {


    @Autowired
    PersonRepository jpacrudRepository;

    @Autowired
    PassportRepository passportRepository;



    public void create(){

        Passport passport = new Passport();
        passport.setPassportNumber("KXY");
        Person person = new Person();
        person.setName("Manoj");
        person.setPassport(passport);
        passportRepository.save(passport);
        jpacrudRepository.save(person);

    }

    public void createWithoutPassport(){
        Person person = new Person();
        person.setName("Manoj");
        jpacrudRepository.save(person);
    }

    public void update(){
        create();
        Person person = jpacrudRepository.findById((long)1).orElse(null);
        person.getPassport().setPassportNumber("abc");
        person.setName("kantha");
        jpacrudRepository.save(person);
        createWithoutPassport();
    }
    @Transactional
    public void delete(){

        Passport passport = new Passport();
        passport.setPassportNumber("KXY");
        Person person = new Person();
        System.out.println(person);
        person.setName("Manoj");
        person.setPassport(passport);
        passportRepository.save(passport);
        jpacrudRepository.save(person);
        System.out.println(person);
        long id = person.getId();
        Person person1 = (Person) jpacrudRepository.findById(id).orElse(null);
        System.out.println(person1);
        jpacrudRepository.delete(person1);

    }


}
