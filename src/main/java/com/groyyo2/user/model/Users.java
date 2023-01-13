package com.groyyo2.user.model;


import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
@Builder
@AllArgsConstructor
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String emailId;

	private String phone;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String fullName;

	@Column(nullable = false)
	private boolean active;

	@LastModifiedDate
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	@CreatedDate
	@Column(name = "created_at", nullable = true, updatable = false)
	private LocalDateTime createdAt;

	@PrePersist
	protected void onCreate() {
		createdAt =  LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		updatedAt = LocalDateTime.now();
	}


}
