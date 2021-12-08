package com.alkemy.disney.disney.Repository.specifications;

import com.alkemy.disney.disney.dto.ActorFiltersDTO;
import com.alkemy.disney.disney.entity.Actor;
import com.alkemy.disney.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;


import java.util.ArrayList;
import java.util.List;

@Component
public class ActorSpecification  {

    @Autowired
    private MovieService movieService;

    public Specification<Actor> getByFilters(ActorFiltersDTO filtersDTO){
        return ((root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if(StringUtils.hasLength(filtersDTO.getName())){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")),
                                "%" + filtersDTO.getName().toLowerCase() + "%"
                        )
                );
            }
            if(!filtersDTO.getAge().equals(null)){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("age")),
                                "%" + filtersDTO.getAge() + "%"
                        )
                );
            }
            if(!filtersDTO.getWeight().equals(null)){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("age")),
                                "%" + filtersDTO.getWeight() + "%"
                        )
                );
            }
            if(!filtersDTO.getIdMovie().equals(null)){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("title")),
                                "%" + movieService.getOneById(filtersDTO.getIdMovie()).getTitle().toLowerCase() + "%"

                        )
                );


            }
            //Eliminar duplicados
            query.distinct(true);

            return  criteriaBuilder.and(predicates.toArray(new Predicate[0]));


        });
    }
}

