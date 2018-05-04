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

    public static final String INSTRUCTOR_ID = "/{instructorId}";
    public static final String INSTRUCTOR_PATH = "instructorId";

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

    @GetMapping(INSTRUCTOR_ID)
    public Mono<Instructor> getInstructor(@PathVariable(INSTRUCTOR_PATH) String instructorId) {
        return repository.findById(instructorId);
    }

    @DeleteMapping(INSTRUCTOR_ID)
    public Mono<Void> deleteInstructor(@PathVariable(INSTRUCTOR_PATH) String id) {
        return repository.deleteById(id);
    }

}
