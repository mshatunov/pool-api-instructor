package com.mshatunov.pool.api.instructor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TimetableEntry {
    @Id
    private String id;
    @Indexed
    private String instructorId;
    @Indexed
    private LocalDate date;
    @Indexed
    private String poolId;
    @Indexed
    private String tubId;
}
