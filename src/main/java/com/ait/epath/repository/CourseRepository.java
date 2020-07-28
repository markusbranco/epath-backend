package com.ait.epath.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ait.epath.model.Course;

public interface CourseRepository  extends JpaRepository<Course, Long>{

	Course findByName(String courseName);

}
