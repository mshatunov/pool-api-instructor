package com.mshatunov.pool.api.instructor.controller;

import com.mshatunov.pool.api.instructor.model.TimetableEntry;
import com.mshatunov.pool.api.instructor.service.TimetableService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mshatunov.pool.api.instructor.controller.InstructorController.INSTRUCTOR_ID;
import static com.mshatunov.pool.api.instructor.controller.InstructorController.INSTRUCTOR_PATH;
import static com.mshatunov.pool.api.instructor.controller.TimetableController.SCHEDULE;

@RestController
@RequestMapping(SCHEDULE + INSTRUCTOR_ID)
@RequiredArgsConstructor
public class TimetableController {

    public static final String SCHEDULE = "/schedule/";

    private final TimetableService service;

    @GetMapping
    public List<TimetableEntry> getInstructorTimetable(@PathVariable(INSTRUCTOR_PATH) String instructorId,
                                                       @RequestParam boolean showOnlyFutureEntries) {
        return service.getInstructorTimetable(instructorId, showOnlyFutureEntries).collectList().block();
    }

}
