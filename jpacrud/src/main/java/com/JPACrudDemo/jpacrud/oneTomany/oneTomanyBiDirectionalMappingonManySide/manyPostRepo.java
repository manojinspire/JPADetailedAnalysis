package com.JPACrudDemo.jpacrud.oneTomany.oneTomanyBiDirectionalMappingonManySide;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface manyPostRepo extends JpaRepository<manyPost,Long> {
}
