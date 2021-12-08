package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.Repository.ActorRepository;
import com.alkemy.disney.disney.Repository.MovieRepository;
import com.alkemy.disney.disney.Repository.specifications.MovieSpecification;
import com.alkemy.disney.disney.dto.MovieBasicDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.dto.MovieFiltersDTO;
import com.alkemy.disney.disney.entity.Movie;
import com.alkemy.disney.disney.exception.ParamNotFound;
import com.alkemy.disney.disney.mapper.MovieMapper;
import com.alkemy.disney.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private MovieSpecification movieSpecification;


    @Override
    public MovieDTO save(MovieDTO dto){
        Movie entity = movieMapper.movieDTO2Entity(dto);
        Movie createdMovie = movieRepository.save(entity);
        return movieMapper.movieEntity2DTO(createdMovie);
    }

    @Override
    public List<MovieDTO> getAllMovies() {
        List<Movie> entities = movieRepository.findAll();
        return movieMapper.movieEntityList2DTOList(entities);
    }

    @Override
    public List<MovieBasicDTO> getAllBasicMovies() {
        List<Movie> entities = movieRepository.findAll();
        return movieMapper.movieEntityList2BasicDTOList(entities);
    }

    @Override
    public Movie getOneById(Long id){
        Optional<Movie> movie = movieRepository.findById(id);
        if(!movie.isPresent()){
            throw new ParamNotFound("Movie not found");
        }
        return movie.get();
    }
    @Override
    public void delete(Long id){this.movieRepository.deleteById(id);}

    @Override
    public MovieDTO update(Long id, MovieDTO movieDTO){
        Optional<Movie> entity = this.movieRepository.findById(id);
        if(!entity.isPresent()){
            throw new ParamNotFound("Movie not found");
        }
        movieDTO = movieMapper.movieEntity2DTO(entity.get());
        return movieDTO;
    }

    @Override
    public List<MovieDTO> getByFilters(String title, Long genreId,String date,String order){
        MovieFiltersDTO fIltersDTO = new MovieFiltersDTO(title, genreId, date, order);
        List<Movie> entities = this.movieRepository.findAll((Sort) this.movieSpecification.getByFilters(fIltersDTO));
        return this.movieMapper.movieEntityList2DTOList(entities);
    }
}
