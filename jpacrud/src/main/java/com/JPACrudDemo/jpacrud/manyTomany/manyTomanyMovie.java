package com.JPACrudDemo.jpacrud.manyTomany;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class manyTomanyMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    public String movieName;

    @ManyToMany(mappedBy = "moviesActed",cascade = CascadeType.ALL)
    public List<manyTomanyActor> actorList;
}
