package com.it211_ss12_02.controller;

import com.it211_ss12_02.model.Course;
import com.it211_ss12_02.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@Slf4j
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<List<Course>> getAll() {
        log.info("Method: getAll() + Endpoint: GET /api/courses");
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getById(@PathVariable Long id) {
        log.info("Method: getById() + Endpoint: GET /api/courses/{}", id);
        try {
            if (id < 0) {
                throw new RuntimeException("ID không thể là số âm!");
            }
            return courseService.getCourseById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (RuntimeException e) {
            log.error("Lỗi hệ thống xảy ra tại CourseController: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<Course> create(@RequestBody Course course) {
        log.info("Method: create() + Endpoint: POST /api/courses");
        return ResponseEntity.ok(courseService.createCourse(course));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@PathVariable Long id, @RequestBody Course course) {
        log.info("Method: update() + Endpoint: PUT /api/courses/{}", id);
        Course updated = courseService.updateCourse(id, course);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }
}
