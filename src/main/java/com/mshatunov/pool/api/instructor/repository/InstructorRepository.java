package com.mshatunov.pool.api.instructor.repository;

import com.mshatunov.pool.api.instructor.model.Instructor;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface InstructorRepository extends ReactiveMongoRepository<Instructor, String> {
}
