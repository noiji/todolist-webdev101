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
		//TodoEntity ����
		TodoEntity entity = TodoEntity.builder().title("My first todo item").build();
		//TodoEntity ����
		repository.save(entity);
		//TodoEntity �˻�
		TodoEntity savedEntity = repository.findById(entity.getId()).get();
		return savedEntity.getTitle();
	}
	
	public List<TodoEntity> create(final TodoEntity entity){
		
		validate(entity);
		repository.save(entity);
		
		log.info("Entity Id {} created.", entity.getId()); //@Slf4j annotation �߰��ϸ� log ��� ����
		return repository.findByUserId(entity.getUserId());
	}
	
	public List<TodoEntity> retrieve(final String userId){
		log.info("Entity UserId {} retrieved.", userId); 
		return repository.findByUserId(userId);
	}
	
	public List<TodoEntity> update(final TodoEntity entity){
		validate(entity);
		
		//�Ѱܹ��� ��ƼƼ id�� �̿��� TodoEntity�� �����´�. Optional�̶�� �� ����!!!!! ���� ���� ���� ���� �־ �׷�����
		final Optional<TodoEntity> original = repository.findById(entity.getId());
		
		if (original.isPresent()) {
			//�� entity�� ���� �����.
			// original = entity; �̷��� �ȵ�.
			final TodoEntity todo = original.get();
			todo.setTitle(entity.getTitle());
			todo.setDone(entity.isDone());
			
			//db���� ����
			repository.save(todo);
		}
		
		return retrieve(entity.getUserId());
	}
	
	public List<TodoEntity> delete(final TodoEntity entity){
			
		validate(entity); // �� ��ó�� optional�� �Ⱦ���? ���� �����Ͱ� ���� ���� �����ٵ�. -> �׷��� try catch�� �ϳ�
		
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