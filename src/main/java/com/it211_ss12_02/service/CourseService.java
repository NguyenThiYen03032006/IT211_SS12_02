package com.it211_ss12_02.service;

import com.it211_ss12_02.model.Course;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CourseService {
    private final List<Course> courses = new ArrayList<>();

    public List<Course> getAllCourses() {
        return courses;
    }

    public Optional<Course> getCourseById(Long id) {
        Optional<Course> course = courses.stream().filter(c -> c.getId().equals(id)).findFirst();
        if (course.isEmpty()) {

            log.warn("Không tìm thấy khóa học với ID: {}", id);
        }
        return course;
    }

    public Course createCourse(Course course) {
        courses.add(course);
        log.info("Tạo mới khóa học thành công: {}", course.getCourseName());
        return course;
    }

    public Course updateCourse(Long id, Course updatedCourse) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId().equals(id)) {
                updatedCourse.setId(id);
                courses.set(i, updatedCourse);
                log.info("Cập nhật khóa học thành công cho ID: {}", id);
                return updatedCourse;
            }
        }
        log.warn("Cập nhật thất bại - Không tìm thấy khóa học với ID: {}", id);
        return null;
    }

    public boolean deleteCourse(Long id) {
        return courses.removeIf(c -> c.getId().equals(id));
    }
}
