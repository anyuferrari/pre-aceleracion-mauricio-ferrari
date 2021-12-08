package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.MovieBasicDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.entity.Movie;

import java.util.List;

public interface MovieService {

    MovieDTO save(MovieDTO dto);
    List<MovieDTO> getAllMovies();
    List<MovieBasicDTO> getAllBasicMovies();
    Movie getOneById(Long id);
    void delete(Long id);
    MovieDTO update(Long id, MovieDTO dto);
    List<MovieDTO> getByFilters(String title, Long genreId,String date,String order);
}
