package com.groyyo2.user.dto;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class KafkaDTO {

	private String type;

	private String subType;

	private String className;

	private Object data;

	private Object metadata;

	private Date creationTime = new Date();

	private String uniqueId = "k" + UUID.randomUUID().toString().replace("-", "");

	public KafkaDTO(String type, String subType, String className, Object data) {
		this.type = type;
		this.subType = subType;
		this.className = className;
		this.data = data;
	}

	public KafkaDTO(String className, Object data) {
		this.className = className;
		this.data = data;
	}
	
	public KafkaDTO(String type, String subType, String className, Object data, Object metadata) {
		this.type = type;
		this.subType = subType;
		this.className = className;
		this.data = data;
		this.metadata = metadata;
	}

}
