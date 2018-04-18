package com.mshatunov.pool.api.instructor.controller;

import com.mshatunov.pool.api.instructor.model.Instructor;
import com.mshatunov.pool.api.instructor.repository.InstructorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class InstructorController {

    private final InstructorRepository repository;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<String> createInstructor(@RequestBody Instructor instructor) {
        return repository.insert(instructor).map(Instructor::getId);
    }

    @GetMapping
    public Flux<Instructor> getInstructors() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Instructor> getInstructor(@PathVariable("id") String id) {
        return repository.findById(id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteInstructor(@PathVariable("id") String id) {
        return repository.deleteById(id);
    }

}
