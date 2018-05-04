package com.mshatunov.pool.api.instructor.service;

import com.mshatunov.pool.api.instructor.controller.dto.AddTimetableEntryRequest;
import com.mshatunov.pool.api.instructor.exception.DateAlreadyOccupiedException;
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
        Mono<String> instructorId = timetableRepository.findByPoolIdAndTubIdAndDate(poolId, tubId, date)
                .map(TimetableEntry::getInstructorId);
        return instructorRepository.findById(instructorId);
    }

    @Override
    public Mono<TimetableEntry> addInstructorTimetableEntry(String instructorId, AddTimetableEntryRequest request) {
        return timetableRepository.findByPoolIdAndTubIdAndDate(request.getPoolId(), request.getTubId(), request.getDate())
                .map(te -> isTimetableEntryExists(request, te))
                .switchIfEmpty(createNewTimeTableEntry(instructorId, request));
    }

    private Mono<TimetableEntry> createNewTimeTableEntry(String instructorId, AddTimetableEntryRequest request) {
        return timetableRepository.insert(TimetableEntry.builder()
                .instructorId(instructorId)
                .poolId(request.getPoolId())
                .tubId(request.getTubId())
                .date(request.getDate())
                .build());
    }

    private TimetableEntry isTimetableEntryExists(AddTimetableEntryRequest request, TimetableEntry te) {
        if (!te.getInstructorId().isEmpty()) {
            throw new DateAlreadyOccupiedException(request.getPoolId(), request.getTubId(), request.getDate(), te.getInstructorId());
        }
        return te;
    }
}
