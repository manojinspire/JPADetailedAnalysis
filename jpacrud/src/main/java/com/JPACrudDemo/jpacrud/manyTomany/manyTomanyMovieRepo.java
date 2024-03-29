package com.JPACrudDemo.jpacrud.manyTomany;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface manyTomanyMovieRepo extends JpaRepository<manyTomanyMovie,Long> {
}
