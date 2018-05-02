package com.mshatunov.pool.api.instructor.service;

import com.mshatunov.pool.api.instructor.model.TimetableEntry;
import reactor.core.publisher.Flux;

public interface TimetableService {
    Flux<TimetableEntry> getInstructorTimetable(String instructorId, boolean showOnlyFutureEntries);
}
