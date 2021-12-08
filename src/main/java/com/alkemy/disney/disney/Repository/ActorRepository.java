package com.alkemy.disney.disney.Repository;

import com.alkemy.disney.disney.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor,Long> {
}
