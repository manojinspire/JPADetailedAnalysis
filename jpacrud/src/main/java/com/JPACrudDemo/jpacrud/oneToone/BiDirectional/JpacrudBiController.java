package com.JPACrudDemo.jpacrud.oneToone.BiDirectional;

import com.JPACrudDemo.jpacrud.oneToone.Unidirectional.JpacrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JpacrudBiController {


        @Autowired
        JpacrudBiService jpacrudService;

        @PostMapping("/oneTone/bi")
        private void create(){
                jpacrudService.create();
        }

        @PutMapping("/oneToone/bi")
        private void update(){

        }

        @DeleteMapping("/oneToone/bi")
        private void delete(){
                jpacrudService.delete();
        }



}
