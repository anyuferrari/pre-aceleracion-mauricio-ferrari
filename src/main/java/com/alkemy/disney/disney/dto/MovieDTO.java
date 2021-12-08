package com.alkemy.disney.disney.dto;

import com.alkemy.disney.disney.entity.Actor;
import com.alkemy.disney.disney.entity.Genre;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
public class MovieDTO {

    private Long id;

    private String image;

    private String title;

    private LocalDate creationDate;


    private Long rating;

    private GenreDTO genre;

    private Long genreId;

    private List<ActorDTO> actors;

    private List<Long> actorsIds;
}
