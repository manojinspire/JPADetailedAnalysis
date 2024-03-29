package com.JPACrudDemo.jpacrud.oneTomany.manyTooneUniDirectional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller1 {

    @Autowired
    ServiceClass serviceClass;
    @PostMapping("manyToone/uni")
    public void create(){
        serviceClass.create();
    }

    @PutMapping("manyToone/uni")
    public void update(){
        serviceClass.update();
    }

    @DeleteMapping("manyToone/uni")
    public void delete(){
        serviceClass.delete();
    }


}
