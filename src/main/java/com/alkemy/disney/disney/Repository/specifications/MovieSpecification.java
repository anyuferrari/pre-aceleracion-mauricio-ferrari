package com.alkemy.disney.disney.Repository.specifications;

import com.alkemy.disney.disney.dto.MovieFiltersDTO;
import com.alkemy.disney.disney.entity.Movie;
import com.alkemy.disney.disney.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieSpecification {

    @Autowired
    private GenreService genreService;

    public Specification<Movie> getByFilters(MovieFiltersDTO filtersDTO){
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(StringUtils.hasLength(filtersDTO.getTitle())){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("title")),
                                "%" + filtersDTO.getTitle().toLowerCase() +"%"
                        )
                );
            }
            if(!filtersDTO.getGenreId().equals(null)){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower((root.get("genre_id"))),
                                "%" + genreService.getOneById(filtersDTO.getGenreId()).getName().toLowerCase() + "%"
                        )
                );
            }
            if (StringUtils.hasLength(filtersDTO.getDate())){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd");
                LocalDate date = LocalDate.parse(filtersDTO.getDate(), formatter);

                predicates.add(
                        criteriaBuilder.equal(root.<LocalDate>get("cration_date"),date)
                );
            }

            //Eliminar duplicados
            query.distinct(true);


            //Para ordenar

            String orderByField = "creation_date";
            query.orderBy(
                    filtersDTO.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)):
                            criteriaBuilder.desc(root.get(orderByField))
            );
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
