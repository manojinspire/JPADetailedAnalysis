package com.JPACrudDemo.jpacrud.manyTomany;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class manyTomanyActor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @ManyToMany(cascade = CascadeType.ALL)
    public List<manyTomanyMovie> moviesActed;


    public String actorName;
}
