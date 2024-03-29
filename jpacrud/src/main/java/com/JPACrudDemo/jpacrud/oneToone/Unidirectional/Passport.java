package com.JPACrudDemo.jpacrud.oneToone.Unidirectional;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String passportNumber;

    // Getters and setters, constructors, other methods...
}