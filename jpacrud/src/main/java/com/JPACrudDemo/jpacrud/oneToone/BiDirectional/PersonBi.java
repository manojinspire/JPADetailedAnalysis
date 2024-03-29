package com.JPACrudDemo.jpacrud.oneToone.BiDirectional;

import com.JPACrudDemo.jpacrud.oneToone.Unidirectional.Passport;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class PersonBi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne()
    private PassportBi passport;

    // Getters and setters, constructors, other methods...
}
