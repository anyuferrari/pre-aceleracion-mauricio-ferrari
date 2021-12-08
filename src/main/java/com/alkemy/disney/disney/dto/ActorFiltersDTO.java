package com.alkemy.disney.disney.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActorFiltersDTO {
    private String name;
    private Long age;
    private Long weight;
    private Long idMovie;

    public ActorFiltersDTO(String name, Long age, Long weight, Long idMovie){
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.idMovie = idMovie;

    }
}
