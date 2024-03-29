package com.JPACrudDemo.jpacrud.oneTomany.oneTomanyBiDirectionalMappingonManySide;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class manyComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id ;
    public String message ;
    @ManyToOne()
    public manyPost manyPost ;
}
