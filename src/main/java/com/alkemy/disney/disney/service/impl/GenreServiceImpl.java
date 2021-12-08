package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.Repository.GenreRepository;
import com.alkemy.disney.disney.dto.GenreDTO;
import com.alkemy.disney.disney.entity.Genre;
import com.alkemy.disney.disney.exception.ParamNotFound;
import com.alkemy.disney.disney.mapper.GenreMapper;
import com.alkemy.disney.disney.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {
    @Autowired
    private GenreMapper genreMapper;
    @Autowired
    private GenreRepository genreRepository;

    @Override
    public GenreDTO save(GenreDTO dto){
        Genre entity = genreMapper.genreDTO2Entity(dto);
        Genre createdGenre = genreRepository.save(entity);
        return genreMapper.genreEntity2DTO(createdGenre);
    }

    @Override
    public List<GenreDTO> getAllGenres() {
        List<Genre> entities = genreRepository.findAll();
        return genreMapper.genreEntityList2DTOList(entities);
    }

    @Override
    public Genre getOneById(Long id){
        Optional<Genre> genre = genreRepository.findById(id);
        if(!genre.isPresent()){
            throw new ParamNotFound("Genre not found");
        }
        return genre.get();
    }
}
