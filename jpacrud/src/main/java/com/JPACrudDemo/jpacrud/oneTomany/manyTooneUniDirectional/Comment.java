package com.JPACrudDemo.jpacrud.oneTomany.manyTooneUniDirectional;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Comment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    public long id;
    public String message;
    @ManyToOne()
    public Post post;
}
