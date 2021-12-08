package com.alkemy.disney.disney.mapper;


import com.alkemy.disney.disney.dto.GenreDTO;
import com.alkemy.disney.disney.entity.Genre;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenreMapper {
    public Genre genreDTO2Entity(GenreDTO dto){
        Genre genreEntity = new Genre();
        genreEntity.setMovie(dto.getMovie());
        genreEntity.setName(dto.getName());
        return genreEntity;
    }

    public GenreDTO genreEntity2DTO(Genre entity){
        GenreDTO dto = new GenreDTO();
        dto.setId(entity.getId());
        dto.setMovie(entity.getMovie());
        dto.setName(entity.getName());
        return dto;
    }


    public List<GenreDTO> genreEntityList2DTOList(List<Genre> entities) {
        List<GenreDTO> dtos = new ArrayList<>();
        for (Genre entity : entities){
            dtos.add(this.genreEntity2DTO(entity));
        }
        return dtos;
    }
}
