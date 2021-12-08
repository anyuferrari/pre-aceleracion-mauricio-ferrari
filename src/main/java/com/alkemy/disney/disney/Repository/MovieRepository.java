package com.alkemy.disney.disney.Repository;

import com.alkemy.disney.disney.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Long> {
}
