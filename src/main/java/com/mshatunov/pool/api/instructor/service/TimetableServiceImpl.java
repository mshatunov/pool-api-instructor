package com.mshatunov.pool.api.instructor.service;

import com.mshatunov.pool.api.instructor.model.Instructor;
import com.mshatunov.pool.api.instructor.model.TimetableEntry;
import com.mshatunov.pool.api.instructor.repository.InstructorRepository;
import com.mshatunov.pool.api.instructor.repository.TimetableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TimetableServiceImpl implements TimetableService {

    private final TimetableRepository timetableRepository;
    private final InstructorRepository instructorRepository;

    @Override
    public Flux<TimetableEntry> getInstructorTimetable(String instructorId, boolean showOnlyFutureEntries) {
        return timetableRepository.findAllByInstructorId(instructorId)
                .filter(e -> !showOnlyFutureEntries || e.getDate().isAfter(LocalDate.now()));
    }

    @Override
    public Mono<Instructor> getInstructorByPoolAndDate(String poolId, String tubId, LocalDate date) {
        String instructorId = timetableRepository.findByPoolIdAndTubIdAndDate(poolId, tubId, date)
                .map(TimetableEntry::getInstructorId)
                .block();
        return instructorRepository.findById(instructorId);
    }
}
