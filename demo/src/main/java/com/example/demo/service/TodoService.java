package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.TodoEntity;
import com.example.demo.persistence.TodoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TodoService { 

	@Autowired
	private TodoRepository repository;
	
	public String testService() {
		//TodoEntity 생성
		TodoEntity entity = TodoEntity.builder().title("My first todo item").build();
		//TodoEntity 저장
		repository.save(entity);
		//TodoEntity 검색
		TodoEntity savedEntity = repository.findById(entity.getId()).get();
		return savedEntity.getTitle();
	}
	
	public List<TodoEntity> create(final TodoEntity entity){
		
		validate(entity);
		repository.save(entity);
		
		log.info("Entity Id {} created.", entity.getId()); //@Slf4j annotation 추가하면 log 사용 가능
		return repository.findByUserId(entity.getUserId());
	}
	
	public List<TodoEntity> retrieve(final TodoEntity entity){

		repository.save(entity);
		
		log.info("Entity Id {} created.", entity.getId()); //@Slf4j annotation 추가하면 log 사용 가능
		return repository.findByUserId(entity.getUserId());
	}
	
	public List<TodoEntity> update(final TodoEntity entity){

		repository.save(entity);
		
		log.info("Entity Id {} created.", entity.getId()); //@Slf4j annotation 추가하면 log 사용 가능
		return repository.findByUserId(entity.getUserId());
	}
	
	public List<TodoEntity> delete(final TodoEntity entity){
			
		repository.save(entity);
		
		log.info("Entity Id {} created.", entity.getId()); //@Slf4j annotation 추가하면 log 사용 가능
		return repository.findByUserId(entity.getUserId());
	}
	
	
	//refactoring한 method
	
	private void validate(final TodoEntity entity) {
		if (entity== null) {
			log.warn("Entity can't be null");
			throw new RuntimeException("Entity can't be null");
		}
		
		if (entity.getUserId() == null) {
			log.warn("No such userId");
			throw new RuntimeException("No such userid");
		}
			
	}
}