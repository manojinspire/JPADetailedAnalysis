package com.JPACrudDemo.jpacrud.oneTomany.oneTomanyBiDirectionalMappingonManySide;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class manyController {

    @Autowired
    manyService manyService;


    @PostMapping("oneTomany/many/bi")
    private void create(){
        manyService.create();

    }


    @PutMapping("oneTomany/many/bi")
    private void update(){

    }


    @DeleteMapping("oneTomany/many/bi")
    private void delete(){
        manyService.delete();
    }
}
