package com.example.demo.service;

import java.util.List;
import java.util.Optional;

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
	
	public List<TodoEntity> retrieve(final String userId){
		log.info("Entity UserId {} retrieved.", userId); 
		return repository.findByUserId(userId);
	}
	
	public List<TodoEntity> update(final TodoEntity entity){
		validate(entity);
		
		//넘겨받은 엔티티 id를 이용해 TodoEntity를 가져온다. Optional이라는 걸 쓴다!!!!! 있을 수도 없을 수도 있어서 그런갑다
		final Optional<TodoEntity> original = repository.findById(entity.getId());
		
		if (original.isPresent()) {
			//새 entity로 덮어 씌운다.
			// original = entity; 이렇게 안됨.
			final TodoEntity todo = original.get();
			todo.setTitle(entity.getTitle());
			todo.setDone(entity.isDone());
			
			//db에도 저장
			repository.save(todo);
		}
		
		return retrieve(entity.getUserId());
	}
	
	public List<TodoEntity> delete(final TodoEntity entity){
			
		validate(entity); // 왜 위처럼 optional은 안쓸까? 없는 데이터가 들어올 수도 있을텐데. -> 그래서 try catch를 하네
		
		try {
			repository.delete(entity);
		} catch(Exception e) {
			log.error("error deleting entity ", entity.getId(), e);
			throw new RuntimeException("error deleting entity " + entity.getId());
		}
		
		return retrieve(entity.getUserId());
	}
	
	
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