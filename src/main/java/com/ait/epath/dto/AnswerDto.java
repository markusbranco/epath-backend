package com.ait.epath.dto;

import java.time.LocalDateTime;

import com.ait.epath.model.Answer;

public class AnswerDto {
	
	private Long id;
	private String message;
	private LocalDateTime criationDate;
	private String authorName;
	
	public AnswerDto(Answer answer) {
		super();
		this.id = answer.getId();
		this.message = answer.getMessage();
		this.criationDate = answer.getCreationDate();
		this.authorName = answer.getAuthor().getName();
	}

	public Long getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}

	public LocalDateTime getCriationDate() {
		return criationDate;
	}

	public String getAuthorName() {
		return authorName;
	}
	
}
