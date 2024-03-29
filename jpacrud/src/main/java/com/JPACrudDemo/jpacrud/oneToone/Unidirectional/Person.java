package com.JPACrudDemo.jpacrud.oneToone.Unidirectional;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne()
    private Passport passport;

    // Getters and setters, constructors, other methods...
}
