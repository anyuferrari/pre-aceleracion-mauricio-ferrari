package com.alkemy.disney.disney.controller;


import com.alkemy.disney.disney.Repository.GenreRepository;
import com.alkemy.disney.disney.Repository.MovieRepository;
import com.alkemy.disney.disney.dto.MovieBasicDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.entity.Genre;
import com.alkemy.disney.disney.entity.Movie;
import com.alkemy.disney.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private MovieRepository movieRepository;

    @GetMapping
    public ResponseEntity<List<MovieBasicDTO>> getAll() {
        List<MovieBasicDTO> movies = movieService.getAllBasicMovies();
        return ResponseEntity.ok().body(movies);
    }
    @GetMapping("/{id}")
    public Movie movieById(@PathVariable Long id) { return movieService.getOneById(id);}

    @PostMapping
    public ResponseEntity<MovieDTO> save(@RequestBody MovieDTO movie){
        MovieDTO createdMovie = movieService.save(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMovie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.movieService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> update ( @PathVariable Long id, @RequestBody MovieDTO movie){
        MovieDTO result = this.movieService.update(id, movie);
        return ResponseEntity.ok().body(result);
    }

    public ResponseEntity<List<MovieDTO>> getDetailsByFilters(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Long genreId,
            @RequestParam(required = false) String date,
            @RequestParam(required = false, defaultValue = "ASC") String order
    ){
        List<MovieDTO> movies = this.movieService.getByFilters(title,genreId,date,order);
        return ResponseEntity.ok(movies);
    }
    }
