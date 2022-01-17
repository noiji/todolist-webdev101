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
@Table(name = "Todo") // ���̺� �̸��� ����. ���� �������� ������ @Entity�� �̸��� ���̺� �̸����� ����

public class TodoEntity {
	@Id //�⺻ Ű�� �� �ʵ忡 ����
	@GeneratedValue(generator="system-uuid") //id�� �ڵ� ����. Ŀ���� generator�� Ȱ���Ͽ�.
	@GenericGenerator(name="system-uuid", strategy = "uuid") //generator�� ���� ����. � ������� ��������.
	private String id;
	private String userId;
	private String title;
	private boolean done; //true: todo �Ϸ��� ���
}