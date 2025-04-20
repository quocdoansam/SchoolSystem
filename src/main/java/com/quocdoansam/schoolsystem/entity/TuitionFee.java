package com.quocdoansam.schoolsystem.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Table(name = "tuition_fees")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TuitionFee {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	String id;

	@OneToOne
	@JoinColumn(name = "student_id", unique = true)
	Student student;

	BigDecimal amount;
	boolean paid;

	@UpdateTimestamp
	LocalDateTime updatedAt;

	@CreationTimestamp
	LocalDateTime createdAt;
}
