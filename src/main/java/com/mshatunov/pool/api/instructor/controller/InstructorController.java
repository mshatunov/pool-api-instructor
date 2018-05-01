package com.mshatunov.pool.api.instructor.controller;

import com.mshatunov.pool.api.instructor.model.Instructor;
import com.mshatunov.pool.api.instructor.repository.InstructorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class InstructorController {

    public static final String INSTRUCTOR_ID = "{instructorId}";
    public static final String INSTRUCTOR_PATH = "instructorId";

    private final InstructorRepository repository;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String createInstructor(@RequestBody Instructor instructor) {
        return repository.insert(instructor).map(Instructor::getId).block();
    }

    @GetMapping
    public List<Instructor> getInstructors() {
        return repository.findAll().collectList().block();
    }

    @GetMapping(INSTRUCTOR_ID)
    public Instructor getInstructor(@PathVariable(INSTRUCTOR_PATH) String id) {
        return repository.findById(id).block();
    }

    @DeleteMapping(INSTRUCTOR_ID)
    public void deleteInstructor(@PathVariable(INSTRUCTOR_PATH) String id) {
        repository.deleteById(id).block();
    }

}
