package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.ActorBasicDTO;
import com.alkemy.disney.disney.dto.ActorDTO;
import com.alkemy.disney.disney.entity.Actor;
import com.alkemy.disney.disney.entity.Movie;
import com.alkemy.disney.disney.service.impl.ActorServiceImpl;

import java.util.List;

public interface ActorService {

    ActorDTO save(ActorDTO dto);

    List<ActorDTO> getAllActors();

    List<ActorBasicDTO> getAllBasicActors();

    Actor getOneById(Long id);

    void delete(Long id);

    ActorDTO update(Long id, ActorDTO dto);

    List<ActorDTO> getByFilters(String name, Long age, Long weight, Long idMovie);

}
