package com.alkemy.disney.disney.controller;

import com.alkemy.disney.disney.dto.ActorBasicDTO;
import com.alkemy.disney.disney.dto.ActorDTO;
import com.alkemy.disney.disney.entity.Actor;
import com.alkemy.disney.disney.entity.Movie;
import com.alkemy.disney.disney.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/characters")
public class ActorController {
    
    @Autowired
    private ActorService actorService;


    @GetMapping
    public ResponseEntity<List<ActorBasicDTO>> getAll() {
        List<ActorBasicDTO> actors = actorService.getAllBasicActors();
        return ResponseEntity.ok().body(actors);
    }

    @PostMapping
    public ResponseEntity<ActorDTO> save(@RequestBody ActorDTO actor){
        ActorDTO createdActor = actorService.save(actor);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdActor);
    }

    @GetMapping("/{id}")
    public Actor actorById(@PathVariable Long id) {return actorService.getOneById(id);}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.actorService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActorDTO> update(@PathVariable Long id, @RequestBody ActorDTO actor){
        ActorDTO result = this.actorService.update(id, actor);
        return ResponseEntity.ok().body(result);
    }

    public ResponseEntity<List<ActorDTO>> getDetailsByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long age,
            @RequestParam(required = false) Long weight,
            @RequestParam(required = false) Long idMovie
            ){
        List<ActorDTO> actor = this.actorService.getByFilters(name, age, weight, idMovie);
        return ResponseEntity.ok(actor);
    }



}
