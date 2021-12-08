package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.Repository.MovieRepository;
import com.alkemy.disney.disney.dto.ActorBasicDTO;
import com.alkemy.disney.disney.dto.ActorDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.entity.Actor;
import com.alkemy.disney.disney.entity.Movie;
import com.alkemy.disney.disney.exception.ParamNotFound;
import com.alkemy.disney.disney.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class ActorMapper {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private ActorService actorService;

    public List<Movie> getMovieList(List<Long> ids){
        List<Movie> result = new ArrayList<>();
        for(Long id : ids){
            Optional<Movie> movie = movieRepository.findById(id);
            if(!movie.isPresent()){
                throw new ParamNotFound("Movie not found");
            }
            result.add(movie.get());
        }
        return result;
    }

    public Actor actorDTO2Entity(ActorDTO dto){
        Actor actorEntity = new Actor();
        List<Movie> moviesEntity = getMovieList(dto.getMoviesId());
        actorEntity.setName(dto.getName());
        actorEntity.setAge(dto.getAge());
        actorEntity.setImage(dto.getImage());
        actorEntity.setWeight(dto.getWeight());
        actorEntity.setStory(dto.getStory());
        actorEntity.setMovies(moviesEntity);
        return actorEntity;
    }

    public ActorDTO actorEntity2DTO (Actor entity){
        ActorDTO dto = new ActorDTO();
        List<MovieDTO> movieDTO = this.movieMapper.movieEntityList2DTOList(entity.getMovies());
        dto.setId(entity.getId());
        dto.setAge(entity.getAge());
        dto.setImage(entity.getImage());
        dto.setWeight(entity.getWeight());
        dto.setStory(entity.getStory());
        dto.setMovies(movieDTO);
        return dto;
    }

    public List<ActorDTO> actorEntityList2DTOList(List<Actor> entities) {
        List<ActorDTO> dtos = new ArrayList<>();
        for (Actor entity : entities){
            dtos.add(this.actorEntity2DTO(entity));
        }
        return dtos;
    }

    public List<ActorBasicDTO> actorEntityList2BasicDTOList(Collection<Actor> entities){
        List<ActorBasicDTO> dtos = new ArrayList<>();
        ActorBasicDTO basicDTO;
        for (Actor entity : entities){
            basicDTO = new ActorBasicDTO();
            basicDTO.setImage(entity.getImage());
            basicDTO.setName(entity.getName());
            dtos.add(basicDTO);
        }
        return dtos;
    }

}
