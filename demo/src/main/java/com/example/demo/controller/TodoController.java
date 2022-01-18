package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.TodoDTO;
import com.example.demo.model.TodoEntity;
import com.example.demo.service.TodoService;

@RestController //�� ��Ʈ�ѷ��� RestController���� ���. http�� ���õ� �ڵ� �� ��û/���� ������ �������� �˾Ƽ� ���ش�.
@RequestMapping("todo") //http://localhost:8080/test

//��Ʈ�ѷ��� ����ڿ��Լ� TodoDTO�� ��û �ٵ�� �Ѱܹް� �̸� TodoEntity�� ��ȯ�� �����ؾ� �Ѵ�. TodoService�� create�� �����ϴ� TodoEntity�� TodoDTO�� ��ȯ�� �����ؾ� �Ѵ�.
public class TodoController { 
	
	@Autowired //�˾Ƽ� ���� ã�� ���� �� ���� �ν��Ͻ� ��� ������ ����. �׷ιǷ� TodoController�� �ʱ�ȭ�� �� �������� �˾Ƽ� TodoService�� �ʱ�ȭ �Ǵ� �˻��� TodoController�� �������ش�. 
	private TodoService service;
	
	@GetMapping("/test")
	public ResponseEntity<?> testTodo() {
		String str = service.testService(); //TodoService�� testService() ���
		List<String> list = new ArrayList<>();
		list.add(str);
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
		return ResponseEntity.ok().body(response);
	}

	@PostMapping
	public ResponseEntity<?> createTodo(@RequestBody TodoDTO dto) {
		try {
			String temporaryUserId = "temporary-user"; // temporary user id.

			// (1) TodoEntity�� ��ȯ�Ѵ�.
			TodoEntity entity = TodoDTO.toEntity(dto);
			
			// (2) id�� null�� �ʱ�ȭ �Ѵ�. ���� ��ÿ��� id�� ����� �ϱ� �����̴�.
			entity.setId(null);
			
			// (3) �ӽ� ���� ���̵� ���� �� �ش�. �� �κ��� 4�� ������ �ΰ����� ���� �� �����̴�. ������ ������ �ΰ� ����� �����Ƿ� �� ����(temporary-user)�� �α��� ���� ��� ������ ���ø����̼��� ���̴�
			entity.setUserId(temporaryUserId);

			// (4) ���񽺸� �̿��� Todo��ƼƼ�� �����Ѵ�.
			List<TodoEntity> entities = service.create(entity);
			
			// (5) �ڹ� ��Ʈ���� �̿��� ���ϵ� ��ƼƼ ����Ʈ�� TodoDTO����Ʈ�� ��ȯ�Ѵ�.
			List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
			
			// (6) ��ȯ�� TodoDTO����Ʈ�� �̿��� ResponseDTO�� �ʱ�ȭ�Ѵ�.
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
			
			// (7) ResponseDTO�� �����Ѵ�.
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			// (8) Ȥ�� ���ܰ� ���� ��� dto��� error�� �޽����� �־� �����Ѵ�.
			String error = e.getMessage();
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@GetMapping
	public ResponseEntity<?> retrieveTodo(){
		String temporaryUserId = "temporary-user";
		List<TodoEntity> entities = service.retrieve(temporaryUserId);
		List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
		ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
	
		return ResponseEntity.ok().body(response);
	
	}
	
	@PutMapping
	public ResponseEntity<?> updateTodo(@RequestBody TodoDTO dto){
		String temporaryUserId = "temporary-user";
		
		TodoEntity entity = TodoDTO.toEntity(dto);
		
		entity.setUserId(temporaryUserId);
		
		List<TodoEntity> entities = service.update(entity);
		List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
		ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
	
		return ResponseEntity.ok().body(response);
	
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteTodo(@RequestBody TodoDTO dto){ 
		/*
		id�� ����� �� ����� ���忡���� �� ���� ������. �� ������ ����ڰ� body�� ä��� �� �ƴϰ� ȭ�� ���� �����ϰڳ�
		���ʿ� Delete �۾��� id�� ���ٰ� �� -> ��¥????? how? ������ title �Ǵ� done�� ���� ���� ���� �۾� �ȵǴµ� ���̵� ���� ���� ������. ���̵� Ű�� ������ �κ��� �����!
		TodoEntity���� @Id �ؿ� �ִ� attribute�� �� Ű��. 
		 */
		try {
			String temporaryUserId = "temporary-user";
			TodoEntity entity = TodoDTO.toEntity(dto);	
			entity.setUserId(temporaryUserId);
			List<TodoEntity> entities = service.delete(entity);
			List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();			
		
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			String error = e.getMessage();
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}
}