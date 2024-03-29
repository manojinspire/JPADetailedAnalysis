package com.JPACrudDemo.jpacrud.oneTomany.manyTooneUniDirectional;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
@Getter
@Setter
public class Post {

    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    public long id;

    public String name ;
}
