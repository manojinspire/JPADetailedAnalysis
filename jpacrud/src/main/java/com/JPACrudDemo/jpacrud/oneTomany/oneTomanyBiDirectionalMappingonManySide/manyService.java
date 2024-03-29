package com.JPACrudDemo.jpacrud.oneTomany.oneTomanyBiDirectionalMappingonManySide;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class manyService {

    @Autowired
    manyCommentRepo manyCommentRepo ;
    @Autowired
    manyPostRepo manyPostRepo ;

    public void create(){
        manyPost manyPost = new manyPost();
        manyComment comment1 = new manyComment();
        comment1.setMessage("hi");
        manyComment comment2 = new manyComment();
        comment2.setMessage("hello");
        comment1.setManyPost(manyPost);
        manyPostRepo.save(manyPost);
        manyCommentRepo.save(comment1);
    }

    public void update(){

    }
    public void delete(){
        create();
        manyComment comment =manyCommentRepo.findById((long) 1).orElse(null);
        comment.setManyPost(null);
        manyCommentRepo.delete(comment);
    }
}
