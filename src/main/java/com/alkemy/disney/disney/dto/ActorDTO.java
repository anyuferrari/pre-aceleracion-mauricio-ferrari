package com.alkemy.disney.disney.dto;

import com.alkemy.disney.disney.entity.Movie;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class ActorDTO {

    private Long id;

    private String name;

    private String image;

    private Long age;

    private Long weight;

    private String story;

    private List<MovieDTO> movies;

    private List<Long> moviesId;
}
