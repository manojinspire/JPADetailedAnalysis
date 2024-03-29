package com.JPACrudDemo.jpacrud.oneToone.BiDirectional;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JpacrudBiService {


    @Autowired
    PersonRepositoryBi jpacrudRepository;

    @Autowired
    PassportRepositoryBi passportRepository;



    public void create(){

        PassportBi passport = new PassportBi();
        passport.setPassportNumber("KXY");
        PersonBi person = new PersonBi();
        person.setName("Manoj");
        person.setPassport(passport);
        passportRepository.save(passport);
        jpacrudRepository.save(person);

    }


    @Transactional
    public void delete(){



    }


}
