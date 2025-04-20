package com.quocdoansam.schoolsystem.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Table(name = "majors")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Major {
	@Id
	@GeneratedValue(generator = "major-id-generator")
	@GenericGenerator(name = "major-id-generator", strategy = "com.quocdoansam.schoolsystem.util.MajorIdGenerator")
	String id;

	@Column(nullable = false, unique = false)
	String name;

	@Column(nullable = false)
	BigDecimal tuitionFees;

	@UpdateTimestamp
	LocalDateTime updatedAt;

	@CreationTimestamp
	LocalDateTime createdAt;

	@OneToMany(mappedBy = "major", cascade = CascadeType.PERSIST, orphanRemoval = false)
	List<Student> students;
}
