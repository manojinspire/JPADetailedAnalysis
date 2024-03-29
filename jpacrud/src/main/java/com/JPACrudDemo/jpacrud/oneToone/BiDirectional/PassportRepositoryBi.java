package com.JPACrudDemo.jpacrud.oneToone.BiDirectional;

import com.JPACrudDemo.jpacrud.oneToone.Unidirectional.Passport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassportRepositoryBi extends JpaRepository<PassportBi, Long> {
}
