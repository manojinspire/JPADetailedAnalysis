package com.JPACrudDemo.jpacrud.oneTomany.oneTomanyUniDirectional;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class oneTomanyPost {

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    public long id;
    public String name ;

    @OneToMany()
    @JoinColumn(name = "post_id")
    public List<oneTomanyComment> oneTomanyCommentsList;
}
