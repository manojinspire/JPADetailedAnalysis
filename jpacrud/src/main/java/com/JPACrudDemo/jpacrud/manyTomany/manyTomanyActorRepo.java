package com.JPACrudDemo.jpacrud.manyTomany;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface manyTomanyActorRepo extends JpaRepository<manyTomanyActor,Long> {
}
