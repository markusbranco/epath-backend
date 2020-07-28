package com.ait.epath.controller.form;

import com.ait.epath.model.Topic;
import com.ait.epath.repository.TopicRepository;

public class UpdateTopicForm {

	
	private String title;
	private String message;
	
	
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

	public Topic update(Long id, TopicRepository topicRepository) {
		Topic topic = topicRepository.getOne(id);
		
		topic.setTitle(this.title);
		topic.setMenssage(this.message);

		return topic;
	}

	
}
