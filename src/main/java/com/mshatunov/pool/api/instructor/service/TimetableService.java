package com.mshatunov.pool.api.instructor.service;

import com.mshatunov.pool.api.instructor.model.Instructor;
import com.mshatunov.pool.api.instructor.model.TimetableEntry;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface TimetableService {
    Flux<TimetableEntry> getInstructorTimetable(String instructorId, boolean showOnlyFutureEntries);
    Mono<Instructor> getInstructorByPoolAndDate(String poolId, String tubId, LocalDate date);
}
