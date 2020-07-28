package com.ait.epath.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ait.epath.model.Topic;
import com.ait.epath.model.TopicStatus;

public class DetailsTopicDto {
	
	private Long id;
	private String title;
	private String message;
	private LocalDateTime creationDate;
	private String authorName;
	private TopicStatus status;
	private List<AnswerDto> answers;
	
	public DetailsTopicDto(Topic topic) {
		this.id = topic.getId();
		this.title = topic.getTitle();
		this.message = topic.getMenssage();
		this.creationDate = topic.getCreationDate();
		this.authorName = topic.getAuthor().getName();
		this.status = topic.getStatus();
		this.answers = new ArrayList<>();
		this.answers.addAll(topic.getAnswers().stream().map(AnswerDto::new).collect(Collectors.toList()));
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getMessage() {
		return message;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public String getAuthorName() {
		return authorName;
	}

	public TopicStatus getStatus() {
		return status;
	}

	public List<AnswerDto> getAnswers() {
		return answers;
	}
	
	
}
