package com.JPACrudDemo.jpacrud.oneTomany.oneTomanyUniDirectional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class oneTomanyServiceClass {

    @Autowired
    oneTomanyPostRepo postRepo;

    @Autowired
    oneTomanyCommentRepo commentRepo;

    public void create(){
        oneTomanyPost post = new oneTomanyPost();
        post.setName("manoj's post");

        oneTomanyComment comment1 = new oneTomanyComment();
        comment1.setMessage1("hi");

        oneTomanyComment comment2 = new oneTomanyComment();
        comment2.setMessage1("programmer");

        List<oneTomanyComment> list = new ArrayList<>();
        list.add(comment1);
        list.add(comment2);
        post.setOneTomanyCommentsList(list);

        commentRepo.save(comment1);
        commentRepo.save(comment2);
        postRepo.save(post);

    }
    public void update(){
        create();
//        oneTomanyComment comment1 = commentRepo.findById((long) 1).orElse(null);
//        oneTomanyComment comment2 = commentRepo.findById((long) 2).orElse(null);
//        comment1.setMessage1("changed comment1");
//        comment2.setMessage1("changed comment2");
        oneTomanyPost post = postRepo.findById((long) 1).orElse(null);
        List<oneTomanyComment> list = post.getOneTomanyCommentsList();
        for(oneTomanyComment comment : list){
            comment.setMessage1("changed");
        }
        oneTomanyComment comment1 = commentRepo.findById((long) 1).orElse(null);
        oneTomanyComment comment2 = commentRepo.findById((long) 2).orElse(null);
        System.out.println(comment2.getMessage1());
    }
    public void delete(){

    }
}
