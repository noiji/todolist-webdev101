package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController //�� ��Ʈ�ѷ��� RestController���� ���. http�� ���õ� �ڵ� �� ��û/���� ������ �������� �˾Ƽ� ���ش�.
@RequestMapping("test") //http://localhost:8080/test

public class TestController { 
	@GetMapping("/testGetMapping")
	public String testControllerWithPath() {
		return "Hello World! testGetMapping";
	}
	@GetMapping("/{id}")
	public String testControllerWithPathVariables(@PathVariable(required=false) int id) { //required = false: �� �Ű������� �� �ʿ��� ���� �ƴ϶�� ��
		return "Hello Word! ID " + id;
	}

}