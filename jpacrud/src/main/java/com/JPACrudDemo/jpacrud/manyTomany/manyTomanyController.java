package com.JPACrudDemo.jpacrud.manyTomany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class manyTomanyController {

    @Autowired
    manyTomanyService manyTomanyService ;

    @PostMapping("manyTomany/uni")
    public void create(){
        manyTomanyService.create();
    }

    @PutMapping("manyTomany/uni")
    public void update(){
        manyTomanyService.update();
    }

    @DeleteMapping("manyTomany/uni")
    public void delete(){
        manyTomanyService.delete();
    }
}
