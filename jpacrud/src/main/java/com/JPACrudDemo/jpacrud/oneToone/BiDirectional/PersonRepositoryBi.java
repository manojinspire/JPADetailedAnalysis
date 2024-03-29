package com.JPACrudDemo.jpacrud.oneToone.BiDirectional;

import com.JPACrudDemo.jpacrud.oneToone.Unidirectional.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepositoryBi extends JpaRepository<PersonBi, Long> {

}
