package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.Repository.ActorRepository;
import com.alkemy.disney.disney.Repository.GenreRepository;
import com.alkemy.disney.disney.Repository.MovieRepository;
import com.alkemy.disney.disney.dto.*;
import com.alkemy.disney.disney.entity.Actor;
import com.alkemy.disney.disney.entity.Genre;
import com.alkemy.disney.disney.entity.Movie;
import com.alkemy.disney.disney.exception.ParamNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class MovieMapper {
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private GenreMapper genreMapper;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private ActorMapper actorMapper;

    public Genre getGenreById(Long id){
        Optional<Genre> genre = genreRepository.findById(id);
        if(!genre.isPresent()) {
            throw new ParamNotFound("It seems we don't have this genre in our database!");
        }
        return genre.get();
    }


    public List<Actor> getActorList(List<Long> ids){
        List<Actor> actorsList = new ArrayList<>();
        for (Long id : ids){
            Optional<Actor> actor = actorRepository.findById(id);
            if(!actor.isPresent()){
                throw new ParamNotFound("Actor not found");
            }
            actorsList.add(actor.get());
        }
        return actorsList;
    }
    public Movie movieDTO2Entity (MovieDTO dto){
        Movie movieEntity = new Movie();
        Genre genreEntity = getGenreById(dto.getGenreId());
        List<Actor> actorEntityList = getActorList(dto.getActorsIds());
        movieEntity.setImage(dto.getImage());
        movieEntity.setTitle(dto.getTitle());
        movieEntity.setCreationDate(dto.getCreationDate());
        movieEntity.setRating( dto.getRating());
        movieEntity.setGenre(genreEntity);
        movieEntity.setActors(actorEntityList);
        return movieEntity;
    }

    public MovieDTO movieEntity2DTO(Movie entity) {
        MovieDTO dto = new MovieDTO();
        GenreDTO genreDTO =this.genreMapper.genreEntity2DTO(entity.getGenre());
        List<ActorDTO> actorDTO = this.actorMapper.actorEntityList2DTOList(entity.getActors());
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setTitle(entity.getTitle());
        dto.setCreationDate(entity.getCreationDate());
        dto.setRating(entity.getRating());
        dto.setGenre(genreDTO);
        dto.setActors(actorDTO);
        return dto;

    }

    public List<MovieDTO> movieEntityList2DTOList(List<Movie> entities){
        List<MovieDTO> dtos = new ArrayList<>();
        for (Movie entity : entities){
            dtos.add(this.movieEntity2DTO(entity));
        }
        return dtos;
    }
    public List<MovieBasicDTO> movieEntityList2BasicDTOList(Collection<Movie> entities){
        List<MovieBasicDTO> dtos = new ArrayList<>();
        MovieBasicDTO basicDTO;
        for (Movie entity : entities){
            basicDTO = new MovieBasicDTO();
            basicDTO.setImage(entity.getImage());
            basicDTO.setTitle(entity.getTitle());
            basicDTO.setCreationDate(entity.getCreationDate());
            dtos.add(basicDTO);
        }
        return dtos;
    }

}
