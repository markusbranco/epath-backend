package com.ait.epath.controller.form;

import com.ait.epath.model.Course;
import com.ait.epath.model.Topic;
import com.ait.epath.repository.CourseRepository;

public class TopicForm {
	
	private String title;
	private String message;
	private String courseName;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public Topic converter(CourseRepository courseRepository) {
		Course course = courseRepository.findByName(courseName);
		return new Topic(title, message, course);
	}

}
