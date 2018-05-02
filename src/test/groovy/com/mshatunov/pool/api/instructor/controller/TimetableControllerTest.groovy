package com.mshatunov.pool.api.instructor.controller

import com.mshatunov.pool.api.instructor.BaseIntegrationTest
import com.mshatunov.pool.api.instructor.model.TimetableEntry
import com.mshatunov.pool.api.instructor.repository.TimetableRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

import java.time.LocalDate

import static org.junit.jupiter.api.Assertions.assertEquals

class TimetableControllerTest extends BaseIntegrationTest {
    public static final String INSTRUCTOR_1 = 'instructor1'
    public static final String POOL_1 = 'pool1'
    public static final String TUB_1 = 'tub1'

    public static final LocalDate DATE_1 = LocalDate.of(2018, 05, 01)
    public static final LocalDate DATE_2 = LocalDate.now().plusDays(1)

    public static final String ID_1 = 'te1'
    public static final String ID_2 = 'te2'


    public static final TimetableEntry TE_1 = TimetableEntry.builder()
            .id(ID_1)
            .instructorId(INSTRUCTOR_1)
            .date(DATE_1)
            .poolId(POOL_1)
            .tubId(TUB_1)
            .build()

    public static final TimetableEntry TE_2 = TimetableEntry.builder()
            .id(ID_2)
            .instructorId(INSTRUCTOR_1)
            .date(DATE_2)
            .poolId(POOL_1)
            .tubId(TUB_1)
            .build()

    @Autowired
    TimetableRepository repository

    @Autowired
    TimetableController controller

    @AfterEach
    void 'clear database'() {
        repository.deleteAll().block()
    }

    @Test
    void 'successfully get teacher\'s timetable'() {
        repository.insert(TE_1).block()
        repository.insert(TE_2).block()

        def timetable = controller.getInstructorTimetable(INSTRUCTOR_1, false)
        assertEquals(2, timetable.size())
    }

    @Test
    void 'successfully get only future teacher\'s timetable'() {
        repository.insert(TE_1).block()
        repository.insert(TE_2).block()

        def timetable = controller.getInstructorTimetable(INSTRUCTOR_1, true)
        assertEquals(1, timetable.size())
        assertEquals(ID_2, timetable.get(0).getId())
    }
}