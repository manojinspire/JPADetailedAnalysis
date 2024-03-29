package com.JPACrudDemo.jpacrud.oneToone.Unidirectional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PassportRepository extends JpaRepository<Passport, Long> {
}
