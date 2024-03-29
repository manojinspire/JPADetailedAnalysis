					JPA-Detailed-Analysis

1-1	UniDirectional:

For create 


For Delete 

public void delete(){

    Passport passport = new Passport();
    passport.setPassportNumber("KXY");
    Person person = new Person();
    person.setName("Manoj");
    person.setPassport(passport);
    jpacrudRepository.save(person);
    long id = person.getId();
    Person person1 = (Person) jpacrudRepository.findById(id).orElse(null);
    jpacrudRepository.delete(person1);

} 


Hibernate: insert into passport (passport_number) values (?)
Hibernate: insert into person (name,passport_id) values (?,?)
Hibernate: delete from person where id=?
Hibernate: delete from passport where id=? 


Exp: here the person has foreign key with passport, so you cannot delete passport without informing or without deleting or without making null for passport_id fields of those whose person table have that passport_id you want to delete. 

so first it will delete those then it will delete the passport. 


	If we remove passport instead of person 



If we don’t provide CASCADETYPE.ALL in person class then if we delete person, it will only delete the person itself it won’t remove passport. 
	If we want to delete both, when if we delete one also then you need to keep cascadetype.all. 


	@Entity
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne()
    private Passport passport;

    // Getters and setters, constructors, other methods...
}


@Entity
@Getter
@Setter
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String passportNumber;

    // Getters and setters, constructors, other methods...
}


For update: 
	
	Here it will work like what you thinking now.




1-1	Bi-directional 



Similar to uni, but you need to add (“mappedBy”) ;  


M-1 Uni-Directional 

@Entity
@Getter
@Setter
public class Post {

    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    public long id;

    public String name ;
}



@Entity
@Getter
@Setter
public class Comment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    public long id;
    public String message;
    @ManyToOne
    public Post post;
}


Create 
public void create(){
    Post post  = new Post();
    post.setName("manoj");
    postRepo.save(post);
}


Hibernate: insert into post (name) values (?)



public void create(){
   Comment comment = new Comment();
   comment.setMessage("i love computer");
   commentRepo.save(comment);

}
 
here post-id will be null.
Hibernate: insert into comment (message,post_id) values (?,?)



Update


Without cascadeType All
public void create(){
   Comment comment = new Comment();
   comment.setMessage("i love computer");
   commentRepo.save(comment);

}
public void update(){
    create();
    Comment coment = commentRepo.findById((long)1).orElse(null);
    Post post = new Post();
    post.setName("manoj");
    coment.setPost(post);
    postRepo.save(post);
    commentRepo.save(coment);
}


Hibernate: insert into comment (message,post_id) values (?,?)
Hibernate: insert into post (name) values (?)
Hibernate: update comment set message=?,post_id=? where id=? 

// we need to first save the post and then only you can save the comment. 

With CascadeType.ALL 

This will take care everything. 
public void create(){
   Comment comment = new Comment();
   comment.setMessage("i love computer");
   commentRepo.save(comment);

}
public void update(){
    create();
    Comment coment = commentRepo.findById((long)1).orElse(null);
    Post post = new Post();
    post.setName("manoj");
    coment.setPost(post);
    commentRepo.save(coment);
}
 
no need to save post again It will only understood and saves. 
Hibernate: insert into comment (message,post_id) values (?,?)
Hibernate: insert into post (name) values (?)
Hibernate: update comment set message=?,post_id=? where id=? 








Delete 

With cascadetype.all 

public void create(){
    Post p1 = new Post();
    p1.setName("hi");
   Comment comment1 = new Comment();
   comment1.setMessage("i love computer");
   Comment comment2 = new Comment();
   comment2.setMessage("i love Maths");
   comment1.setPost(p1);
   comment2.setPost(p1);
   commentRepo.save(comment1);
   commentRepo.save(comment2);
}
public void delete(){
    create();
    Post post=postRepo.findById((long)1).orElse(null);
    postRepo.delete(post);
}
 

Hibernate: insert into post (name) values (?)
Hibernate: insert into comment (message,post_id) values (?,?)
Hibernate: insert into comment (message,post_id) values (?,?)
The values are not deleting itself. Here 

So we need to delete those comments first and then u can delete post.  


public void delete(){
    create();
    Comment comment1 = commentRepo.findById((long)1).orElse(null);
    Comment comment2 = commentRepo.findById((long)2).orElse(null);
    commentRepo.delete(comment1);
    Post post=postRepo.findById((long)1).orElse(null);
    postRepo.delete(post);
}


in this we deleted only one comment having post_id 1 . so we have another comment which have post_id Is 1. So post wont delete, but comment1 will be deleted. 
Hibernate: insert into post (name) values (?)
Hibernate: insert into comment (message,post_id) values (?,?)
Hibernate: insert into comment (message,post_id) values (?,?)
Hibernate: delete from comment where id=?




With cascadetype.all 
public void create(){
    Post p1 = new Post();
    p1.setName("hi");
   Comment comment1 = new Comment();
   comment1.setMessage("i love computer");
   Comment comment2 = new Comment();
   comment2.setMessage("i love Maths");
   comment1.setPost(p1);
   comment2.setPost(p1);
   commentRepo.save(comment1);
   commentRepo.save(comment2);
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
 
Hibernate: insert into post (name) values (?)
Hibernate: insert into comment (message,post_id) values (?,?)
Hibernate: insert into comment (message,post_id) values (?,?)
Hibernate: delete from comment where id=?
Hibernate: update comment set message=?,post_id=? where id=?
Hibernate: delete from comment where id=?
Hibernate: delete from post where id=? 

If there are nothing of parent rows then the value for which it mapped also deleted. Like here if we delete cascade1 and 2 then hibernate will delete post_id also. 


Without Cascade type.all

We need to save postrepo first before saving comment, in create method.
	Here it won’t delete the post.
Hibernate: insert into post (name) values (?)
Hibernate: insert into comment (message,post_id) values (?,?)
Hibernate: insert into comment (message,post_id) values (?,?)
Hibernate: delete from comment where id=?
Hibernate: delete from comment where id=?




One-To-Many Uni-Directional 

@Entity
@Getter
@Setter
public class oneTomanyComment {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    public long id;
    public String message1;

}


@Entity
@Setter
@Getter
public class oneTomanyPost {

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    public long id;
    public String name ;
    @OneToMany
    public List<oneTomanyComment> oneTomanyCommentsList;
}


Without cascadeType.All  & without Join Column. 


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


we know right without cascade type.all we should need to save first the .
	if post have list of comments, then we need to save all the comments first in database, and then we need to store that post. Otherwise it will throw error. 


Hibernate: insert into one_tomany_comment (message1) values (?)
Hibernate: insert into one_tomany_comment (message1) values (?)
Hibernate: insert into one_tomany_post (name) values (?)
Hibernate: insert into one_tomany_post_one_tomany_comments_list (one_tomany_post_id,one_tomany_comments_list_id) values (?,?)
Hibernate: insert into one_tomany_post_one_tomany_comments_list (one_tomany_post_id,one_tomany_comments_list_id) values (?,?) 



Since no join column it will create a new mapping table. 





Without cascade, @joincolumn 


public void update(){
    create();
    oneTomanyComment comment1 = commentRepo.findById((long) 1).orElse(null);
    oneTomanyComment comment2 = commentRepo.findById((long) 2).orElse(null);
    comment1.setMessage1("changed comment1");
    comment2.setMessage1("changed comment2");
    commentRepo.save(comment1);
    commentRepo.save(comment2);
    oneTomanyPost post = postRepo.findById((long) 1).orElse(null);
    List<oneTomanyComment> list = post.getOneTomanyCommentsList();
    for(oneTomanyComment comment : list){
        System.out.println(comment.getMessage1());
    }
}




Hibernate: insert into one_tomany_comment (message1) values (?)
Hibernate: insert into one_tomany_comment (message1) values (?)
Hibernate: insert into one_tomany_post (name) values (?)
Hibernate: insert into one_tomany_post_one_tomany_comments_list (one_tomany_post_id,one_tomany_comments_list_id) values (?,?)
Hibernate: insert into one_tomany_post_one_tomany_comments_list (one_tomany_post_id,one_tomany_comments_list_id) values (?,?)
Hibernate: update one_tomany_comment set message1=? where id=?
Hibernate: update one_tomany_comment set message1=? where id=?
changed comment1
changed comment2 


see here I didn’t saved anything to post, but the values are updated, because it created a mapping table, and mapping table consists only id’s so they don’t change. 





With join column. 


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


we get the value changed, because same table reference. 



1.	Model == Entity 
For Dto no annotation is required. 

UUID ->  


// join column is only for uni-directional mapping. 
// mapped by is only for bi-directional mapping. 
ONE-TO-MANY Bi-Directional 

@Entity
@Setter
@Getter
public class manyComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id ;
    public String message ;
    @ManyToOne
    public manyPost manyPost ;
}

@Entity
@Getter
@Setter
public class manyPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    @OneToMany(mappedBy = "manyPost")
    public List<manyComment> manyCommentList;
}
 


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


if we don’t save post first it will through error, without cascadetype.all  
so we need to save main table first, and then we need to save the foreignkey table. 



public class manyComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id ;
    public String message ;
    @ManyToOne(cascade = CascadeType.ALL)
    public manyPost manyPost ;
}


Cascadetype.all should be in the foreign key mapped table side. 


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


post 

comments 

comments contain post_id -> fk table 

we can’t save the comments without saving the post first if there is no cascade. 

But we can save the post without saving the comments first even there is no cascade. 



Delete -> 





		Many-Many 


Here we use mappedBy to avoid creating two mapping tables. 



Uni-Directional Mapping

@Entity
@Getter
@Setter
public class manyTomanyMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    public String movieName;
}
 
@Entity
@Setter
@Getter
public class manyTomanyActor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    @ManyToMany
    public List<manyTomanyMovie> moviesActed;
    public String actorName;
}
 

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
    // manyTomany is on actorside for uni-dir
    actor1.setMoviesActed(movieList);
    manyTomanyActorRepo.save(actor1);
}

here it will thrown error, because since we didn’t kept cascade and we are trying to save the actor1 which has foreignkey of movies list. So to make it successful you need to save movies first and then actor. 



Solution:-

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
    // manyTomany is on actorside for uni-dir
    manyTomanyMovieRepo.save(movie1);
    manyTomanyMovieRepo.save(movie2);
    actor1.setMoviesActed(movieList);
    manyTomanyActorRepo.save(actor1);
}

Hibernate: insert into many_tomany_movie (movie_name) values (?)
Hibernate: insert into many_tomany_movie (movie_name) values (?)
Hibernate: insert into many_tomany_actor (actor_name) values (?)
Hibernate: insert into many_tomany_actor_movies_acted (many_tomany_actor_id,movies_acted_id) values (?,?)
Hibernate: insert into many_tomany_actor_movies_acted (many_tomany_actor_id,movies_acted_id) values (?,?) 




Bi-directional 

Without cascade on Actor-Side
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
    manyTomanyMovieRepo.save(movie1);
    manyTomanyMovieRepo.save(movie2);
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


Hibernate: insert into many_tomany_movie (movie_name) values (?)
Hibernate: insert into many_tomany_movie (movie_name) values (?)
Hibernate: insert into many_tomany_actor (actor_name) values (?)
Hibernate: insert into many_tomany_actor_movies_acted (actor_list_id,movies_acted_id) values (?,?)
Hibernate: insert into many_tomany_actor_movies_acted (actor_list_id,movies_acted_id) values (?,?)
Hibernate: delete from many_tomany_actor_movies_acted where actor_list_id=?
Hibernate: delete from many_tomany_actor where id=? 
Having cascadeType.all on both sides 
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

Hibernate: insert into many_tomany_movie (movie_name) values (?)
Hibernate: insert into many_tomany_movie (movie_name) values (?)
Hibernate: insert into many_tomany_actor (actor_name) values (?)
Hibernate: insert into many_tomany_actor_movies_acted (actor_list_id,movies_acted_id) values (?,?)
Hibernate: insert into many_tomany_actor_movies_acted (actor_list_id,movies_acted_id) values (?,?)
Hibernate: delete from many_tomany_actor_movies_acted where actor_list_id=?
Hibernate: delete from many_tomany_movie where id=?
Hibernate: delete from many_tomany_movie where id=?
Hibernate: delete from many_tomany_actor where id=?
