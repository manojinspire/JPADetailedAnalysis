package com.JPACrudDemo.jpacrud.oneTomany.oneTomanyBiDirectionalMappingonManySide;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class manyPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @OneToMany(mappedBy = "manyPost",cascade = CascadeType.ALL)
    public List<manyComment> manyCommentList;
}
