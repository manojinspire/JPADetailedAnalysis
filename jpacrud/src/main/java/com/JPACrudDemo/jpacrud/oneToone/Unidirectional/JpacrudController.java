package com.JPACrudDemo.jpacrud.oneToone.Unidirectional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JpacrudController {


        @Autowired
        JpacrudService jpacrudService;

        @PostMapping("/oneTone/uni")
        private void create(){
                jpacrudService.create();
        }

        @PutMapping("/oneToone/uni")
        private void update(){
                jpacrudService.update();
        }

        @DeleteMapping("/oneToone/uni")
        private void delete(){
                jpacrudService.delete();
        }



}
