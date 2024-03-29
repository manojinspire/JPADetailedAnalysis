package com.JPACrudDemo.jpacrud.oneToone.BiDirectional;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PassportBi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String passportNumber;

    @OneToOne(mappedBy = "passport")
    private PersonBi person;

    // Getters and setters, constructors, other methods...
}