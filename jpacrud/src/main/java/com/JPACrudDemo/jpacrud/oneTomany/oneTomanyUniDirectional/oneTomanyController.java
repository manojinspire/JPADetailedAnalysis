package com.JPACrudDemo.jpacrud.oneTomany.oneTomanyUniDirectional;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
public class oneTomanyController {


    @Autowired
    oneTomanyServiceClass oneTomanyServiceClass;

    @PostMapping("oneTomany/uni")
    public void create(){
        oneTomanyServiceClass.create();
    }

    @PutMapping("oneTomany/uni")
    public void update(){
        oneTomanyServiceClass.update();
    }

    @DeleteMapping("oneTomany/uni")
    public void delete(){
        oneTomanyServiceClass.delete();
    }


}
