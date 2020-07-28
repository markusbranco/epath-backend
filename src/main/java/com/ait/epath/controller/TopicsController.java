package com.ait.epath.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ait.epath.controller.form.TopicForm;
import com.ait.epath.controller.form.UpdateTopicForm;
import com.ait.epath.dto.DetailsTopicDto;
import com.ait.epath.dto.TopicDto;
import com.ait.epath.model.Topic;
import com.ait.epath.repository.CourseRepository;
import com.ait.epath.repository.TopicRepository;

@RestController
@RequestMapping("/topics")
public class TopicsController {
	
	@Autowired
	private TopicRepository topicRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@GetMapping
	@Cacheable(value = "topicsList")
	public Page<TopicDto> list(@RequestParam(required = false) String courseName,
			@PageableDefault(sort = "creationDate", direction = Direction.DESC, page = 0, size = 10)	Pageable pagination) {
		
		//Pageable pagination = PageRequest.of(page, qtd, Direction.ASC, ordenation);
		
		if (courseName == null) {
			Page<Topic> topics = topicRepository.findAll(pagination);
			return TopicDto.converter(topics);
		} else {
			Page<Topic> topics = topicRepository.findByCourseName(courseName, pagination);
			return TopicDto.converter(topics);
		}
		
		
		
	}
	
	@PostMapping
	@Transactional
	@CacheEvict(value = "topicsList", allEntries = true)
	public ResponseEntity<TopicDto> register(@RequestBody TopicForm form, UriComponentsBuilder uriBuilder) {
		Topic topic  = form.converter(courseRepository);
		topicRepository.save(topic);
		
		URI uri = uriBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicDto(topic));
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetailsTopicDto> detail(@PathVariable Long id) {
		Optional<Topic> topic = topicRepository.findById(id);
		if (topic.isPresent()) {
			return ResponseEntity.ok(new DetailsTopicDto(topic.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional //Tells the spring to commit the transaction ad the end of the method
	@CacheEvict(value = "topicsList", allEntries = true)
	public ResponseEntity<TopicDto> update(@PathVariable Long id, @RequestBody UpdateTopicForm form) {
		Optional<Topic> optional = topicRepository.findById(id);
		if (optional.isPresent()) {
			Topic topic = form.update(id, topicRepository);
			return ResponseEntity.ok(new TopicDto(topic));
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "topicsList", allEntries = true)
	public ResponseEntity<?> remove(@PathVariable Long id) {
		Optional<Topic> optional = topicRepository.findById(id);
		if (optional.isPresent()) {
			topicRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
		
		
	}
}
