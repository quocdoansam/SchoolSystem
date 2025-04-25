package com.quocdoansam.schoolsystem.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Table(name = "users")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class User {
	@Id
	@GeneratedValue(generator = "user-id-generator")
	@GenericGenerator(name = "user-id-generator", strategy = "com.quocdoansam.schoolsystem.util.UserIdGenerator")
	String id;

	@Column(nullable = false)
	String password;

	@Column(nullable = false)
	String name;

	LocalDate dob;
	String gender;
	String hometown;
	String address;

	@Column(unique = true)
	String phoneNumber;

	@Column(unique = true)
	String email;
	Set<String> roles;

	@UpdateTimestamp
	LocalDateTime updatedAt;
	@CreationTimestamp
	LocalDateTime createdAt;
}
