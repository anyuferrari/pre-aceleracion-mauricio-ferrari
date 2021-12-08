package com.alkemy.disney.disney.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter

public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String image;

    private String name;

    private Long age;

    private Long weight;

    private String story;
    @JsonIgnore
    @ManyToMany(mappedBy = "actors", cascade = CascadeType.ALL)
    private List<Movie> movies = new ArrayList<>();

    public void addMovie(Movie movie){this.movies.add(movie);}
    public void removeMovie(Movie movie){this.movies.remove(movie);}


}

