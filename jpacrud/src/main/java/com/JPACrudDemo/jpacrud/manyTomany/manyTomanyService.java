package com.JPACrudDemo.jpacrud.manyTomany;

import org.hibernate.annotations.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class manyTomanyService {

    @Autowired
    manyTomanyActorRepo manyTomanyActorRepo ;

    @Autowired
    manyTomanyMovieRepo manyTomanyMovieRepo ;


    public void create(){
        manyTomanyMovie movie1 = new manyTomanyMovie();
        movie1.setMovieName("Pavanism");
        manyTomanyMovie movie2 = new manyTomanyMovie();
        movie2.setMovieName("Bahubali");
        manyTomanyActor actor1 = new manyTomanyActor();
        actor1.setActorName("Arani Pavan");
        manyTomanyActor actor2 = new manyTomanyActor();
        actor2.setActorName("Prabhas");
        List<manyTomanyMovie> movieList = new ArrayList<>();
        movieList.add(movie1);
        movieList.add(movie2);
        List<manyTomanyActor> actorList = new ArrayList<>();
        actorList.add(actor1);
        actorList.add(actor2);
//        manyTomanyMovieRepo.save(movie1);
//        manyTomanyMovieRepo.save(movie2);
        // manyTomany is on actorside for uni-dir
        actor1.setMoviesActed(movieList);
        manyTomanyActorRepo.save(actor1);
    }

    public void update(){

    }
    public void delete(){
        create();
        manyTomanyActor actor1 = manyTomanyActorRepo.findById((long) 1 ).orElse(null);
        manyTomanyActorRepo.delete(actor1);


    }
}
