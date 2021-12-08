package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.GenreDTO;
import com.alkemy.disney.disney.entity.Genre;

import java.util.List;

public interface GenreService {
    GenreDTO save(GenreDTO dto);

    List<GenreDTO> getAllGenres();

    Genre getOneById(Long id);
}
