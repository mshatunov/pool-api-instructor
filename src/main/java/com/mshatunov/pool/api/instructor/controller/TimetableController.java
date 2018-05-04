package com.mshatunov.pool.api.instructor.controller;

import com.mshatunov.pool.api.instructor.controller.dto.AddTimetableEntryRequest;
import com.mshatunov.pool.api.instructor.model.Instructor;
import com.mshatunov.pool.api.instructor.model.TimetableEntry;
import com.mshatunov.pool.api.instructor.service.TimetableService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static com.mshatunov.pool.api.instructor.controller.InstructorController.INSTRUCTOR_ID;
import static com.mshatunov.pool.api.instructor.controller.InstructorController.INSTRUCTOR_PATH;
import static com.mshatunov.pool.api.instructor.controller.TimetableController.SCHEDULE;

@RestController
@RequestMapping(SCHEDULE)
@RequiredArgsConstructor
public class TimetableController {

    public static final String SCHEDULE = "/schedule";

    public static final String POOL_ID = "/{poolId}";
    public static final String POOL_PATH = "poolId";

    public static final String TUB_ID = "/{tubId}";
    public static final String TUB_PATH = "tubId";

    public static final String DATE = "/{date}";
    public static final String DATE_PATH = "date";

    private final TimetableService service;

    @GetMapping(INSTRUCTOR_ID)
    public Flux<TimetableEntry> getInstructorTimetable(@PathVariable(INSTRUCTOR_PATH) String instructorId,
                                                       @RequestParam(defaultValue = "true") boolean showOnlyFutureEntries) {
        return service.getInstructorTimetable(instructorId, showOnlyFutureEntries);
    }

    @PostMapping(INSTRUCTOR_ID)
    public Mono<TimetableEntry> addInstructorTimetableEntry(@PathVariable(INSTRUCTOR_PATH) String instructorId,
                                                            @RequestBody AddTimetableEntryRequest request) {
        return service.addInstructorTimetableEntry(instructorId, request);
    }

    @GetMapping(POOL_ID + TUB_ID + DATE)
    public Mono<Instructor> getInstructorTimetableByPoolAndDate(@PathVariable(POOL_PATH) String poolId,
                                                                @PathVariable(TUB_PATH) String tubId,
                                                                @PathVariable(DATE_PATH) String date) {
        return service.getInstructorByPoolAndDate(poolId, tubId, LocalDate.parse(date));
    }

}
