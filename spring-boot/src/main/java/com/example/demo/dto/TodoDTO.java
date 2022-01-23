package com.example.demo.dto;

import com.example.demo.model.TodoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data

public class TodoDTO {
	private String id;
	private String title;
	private boolean done; //보안을 위하여 userId는 포함하지 않음. 추후 스프링 시큐리티로 인증 구현 예정.
	
	public TodoDTO(final TodoEntity entity) {
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.done = entity.isDone();
	}
	
	//컨트롤러는 사용자에게서 TodoDTO를 요청 바디로 넘겨받고 이를 TodoEntity로 변환해 저장해야 한다.
	public static TodoEntity toEntity(final TodoDTO dto) {
		return TodoEntity.builder()
				.id(dto.getId())
				.title(dto.getTitle())
				.done(dto.isDone())
				.build();
	}
}