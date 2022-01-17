package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController //이 컨트롤러가 RestController임을 명시. http와 관련된 코드 및 요청/응답 매핑을 스프링이 알아서 해준다.
@RequestMapping("test") //http://localhost:8080/test

public class TestController { 
	@GetMapping("/testGetMapping")
	public String testControllerWithPath() {
		return "Hello World! testGetMapping";
	}
	@GetMapping("/{id}")
	public String testControllerWithPathVariables(@PathVariable(required=false) int id) { //required = false: 이 매개변수가 꼭 필요한 것은 아니라는 뜻
		return "Hello Word! ID " + id;
	}

}