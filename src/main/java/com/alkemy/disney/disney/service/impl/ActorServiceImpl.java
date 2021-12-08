package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.Repository.ActorRepository;
import com.alkemy.disney.disney.Repository.specifications.ActorSpecification;
import com.alkemy.disney.disney.dto.ActorBasicDTO;
import com.alkemy.disney.disney.dto.ActorDTO;
import com.alkemy.disney.disney.dto.ActorFiltersDTO;
import com.alkemy.disney.disney.entity.Actor;
import com.alkemy.disney.disney.exception.ParamNotFound;
import com.alkemy.disney.disney.mapper.ActorMapper;
import com.alkemy.disney.disney.service.ActorService;
import com.alkemy.disney.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    private ActorMapper actorMapper;
    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private MovieService movieService;
    @Autowired
    private ActorSpecification actorSpecification;


    @Override
    public ActorDTO save(ActorDTO dto){
        Actor entity = actorMapper.actorDTO2Entity(dto);
        Actor createdActor = actorRepository.save(entity);
        return actorMapper.actorEntity2DTO(createdActor);
    }

    @Override
    public List<ActorDTO> getAllActors() {
        List<Actor> entities = actorRepository.findAll();
        return actorMapper.actorEntityList2DTOList(entities);
    }

    @Override
    public List<ActorBasicDTO> getAllBasicActors() {
        List<Actor> entities = actorRepository.findAll();
        return actorMapper.actorEntityList2BasicDTOList(entities);
    }

    @Override
    public Actor getOneById(Long id){
        Optional<Actor> result = actorRepository.findById(id);
        if(!result.isPresent()){
            throw new ParamNotFound("Character not found");
        }
        return result.get();
    }

    @Override
    public void delete(Long id) {this.actorRepository.deleteById(id);}

    @Override
    public ActorDTO update (Long id, ActorDTO actorDTO){
        Optional<Actor> entity = this.actorRepository.findById(id);
        if(!entity.isPresent()){
            throw new ParamNotFound("Character not found");
        }
        actorDTO = actorMapper.actorEntity2DTO(entity.get());
        return actorDTO;
    }

    @Override
    public List<ActorDTO> getByFilters(String name, Long age, Long weight, Long idMovie){
        ActorFiltersDTO filtersDTO = new ActorFiltersDTO(name, age, weight, idMovie);
        List<Actor> entities = this.actorRepository.findAll((Sort) this.actorSpecification.getByFilters(filtersDTO));
        return this.actorMapper.actorEntityList2DTOList(entities);
    }
}
