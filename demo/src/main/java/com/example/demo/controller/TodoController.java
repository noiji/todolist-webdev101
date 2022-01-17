package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.service.TodoService;

@RestController //�� ��Ʈ�ѷ��� RestController���� ���. http�� ���õ� �ڵ� �� ��û/���� ������ �������� �˾Ƽ� ���ش�.
@RequestMapping("todo") //http://localhost:8080/test

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
}