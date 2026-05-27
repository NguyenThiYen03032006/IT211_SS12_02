package com.it211_ss12_02.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    private Long id;
    private String courseName;
    private String instructor;
    private Integer durationHours;
    private Double fee;
}