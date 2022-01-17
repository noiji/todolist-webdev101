package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Todo") // 테이블 이름을 지정. 만일 지정하지 않으면 @Entity의 이름을 테이블 이름으로 간주

public class TodoEntity {
	@Id //기본 키가 될 필드에 지정
	@GeneratedValue(generator="system-uuid") //id를 자동 생성. 커스텀 generator을 활용하여.
	@GenericGenerator(name="system-uuid", strategy = "uuid") //generator에 대한 설정. 어떤 방식으로 생성할지.
	private String id;
	private String userId;
	private String title;
	private boolean done; //true: todo 완료한 경우
}