package com.quocdoansam.schoolsystem.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import com.quocdoansam.schoolsystem.converter.YearMonthAttributeConverter;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Table(name = "salaries")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Salary {
    @Id
    @GeneratedValue(generator = "salary-id-generator")
    @GenericGenerator(name = "salary-id-generator", strategy = "com.quocdoansam.schoolsystem.util.SalaryIdGenerator")
    String id;

    @Convert(converter = YearMonthAttributeConverter.class)
    YearMonth monthSalary;

    BigDecimal amount;
    Boolean paid;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    Teacher teacher;

    @UpdateTimestamp
    LocalDateTime updatedAt;

    @CreationTimestamp
    LocalDateTime createdAt;
}
