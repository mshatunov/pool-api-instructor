package com.mshatunov.pool.api.instructor.exception;

import java.time.LocalDate;

public class DateAlreadyOccupiedException extends InstructorException {
    public DateAlreadyOccupiedException(String poolId, String tubId, LocalDate date, String instructorId
    ) {
        super(String.format("Date %s in pool %s in tub %s is already occupied by %s", date.toString(), poolId, tubId, instructorId));
    }
}
