package com.example.demo.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data

public class ResponseDTO<T> { //����Ʈ�� ��ȯ
	private String error;
	private List<T> data;
}