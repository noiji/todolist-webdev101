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

@RestController //이 컨트롤러가 RestController임을 명시. http와 관련된 코드 및 요청/응답 매핑을 스프링이 알아서 해준다.
@RequestMapping("todo") //http://localhost:8080/test

public class TodoController { 
	
	@Autowired //알아서 빈을 찾은 다음 그 빈을 인스턴스 멤버 변수에 연결. 그로므로 TodoController를 초기화할 때 스프링은 알아서 TodoService를 초기화 또는 검색해 TodoController에 주입해준다. 
	private TodoService service;
	
	@GetMapping("/test")
	public ResponseEntity<?> testTodo() {
		String str = service.testService(); //TodoService의 testService() 사용
		List<String> list = new ArrayList<>();
		list.add(str);
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
		return ResponseEntity.ok().body(response);
	}
}