package com.JPACrudDemo.jpacrud.oneTomany.oneTomanyUniDirectional;

import com.JPACrudDemo.jpacrud.oneTomany.manyTooneUniDirectional.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class oneTomanyComment {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    public long id;
    public String message1;


}
