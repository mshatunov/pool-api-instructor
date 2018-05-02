package com.mshatunov.pool.api.instructor.repository;

import com.mshatunov.pool.api.instructor.model.TimetableEntry;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface TimetableRepository extends ReactiveMongoRepository<TimetableEntry, String> {
    Flux<TimetableEntry> findAllByInstructorId(String instructorId);
}
