package com.JPACrudDemo.jpacrud.oneTomany.oneTomanyUniDirectional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface oneTomanyCommentRepo extends JpaRepository<oneTomanyComment, Long> {
}
