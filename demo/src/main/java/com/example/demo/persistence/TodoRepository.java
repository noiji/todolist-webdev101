package com.example.demo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.TodoEntity;

import java.util.List;

//TodoRepository는 JpaRepository를 상송하므로 JpaRepository가 제공하는 메서드를 사용할 수 있다.
//엔터티 저장에는 save 메서드를, 새 Todo 리스트 반환에는 findByUserId 메서드를 사용.
@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Long>{
	List<TodoEntity> findByUserId(String userId);
}