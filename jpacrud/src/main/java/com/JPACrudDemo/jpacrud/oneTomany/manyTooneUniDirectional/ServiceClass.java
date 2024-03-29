package com.JPACrudDemo.jpacrud.oneTomany.manyTooneUniDirectional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceClass {

    @Autowired
    PostRepo postRepo;

    @Autowired
    CommentRepo commentRepo;

    public void create(){
        Post p1 = new Post();
        p1.setName("hi");
       Comment comment1 = new Comment();
       comment1.setMessage("i love computer");
       Comment comment2 = new Comment();
       comment2.setMessage("i love Maths");
       comment1.setPost(p1);
       comment2.setPost(p1);
       postRepo.save(p1);
       commentRepo.save(comment1);
       commentRepo.save(comment2);
    }
    public void update(){
        create();
        Comment coment = commentRepo.findById((long)1).orElse(null);
        Post post = new Post();
        post.setName("manoj");
        coment.setPost(post);
        commentRepo.save(coment);
    }
    public void delete(){
        create();
        Comment comment1 = commentRepo.findById((long)1).orElse(null);
        Comment comment2 = commentRepo.findById((long)2).orElse(null);
        commentRepo.delete(comment1);
        commentRepo.delete(comment2);
//        Post post=postRepo.findById((long)1).orElse(null);
//        postRepo.delete(post);
    }
}
