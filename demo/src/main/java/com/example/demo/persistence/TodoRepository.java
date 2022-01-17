package com.example.demo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.TodoEntity;

import java.util.List;

//TodoRepository�� JpaRepository�� ����ϹǷ� JpaRepository�� �����ϴ� �޼��带 ����� �� �ִ�.
//����Ƽ ���忡�� save �޼��带, �� Todo ����Ʈ ��ȯ���� findByUserId �޼��带 ���.
@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Long>{
	List<TodoEntity> findByUserId(String userId);
}