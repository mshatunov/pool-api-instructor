package com.mshatunov.pool.api.instructor.service;

import com.mshatunov.pool.api.instructor.model.TimetableEntry;
import com.mshatunov.pool.api.instructor.repository.TimetableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TimetableServiceImpl implements TimetableService {

    private final TimetableRepository repository;

    @Override
    public Flux<TimetableEntry> getInstructorTimetable(String instructorId, boolean showOnlyFutureEntries) {
        return repository.findAllByInstructorId(instructorId)
                .filter(e -> !showOnlyFutureEntries || e.getDate().isAfter(LocalDate.now()));
    }
}
