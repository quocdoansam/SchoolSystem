package com.quocdoansam.schoolsystem.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import com.quocdoansam.schoolsystem.converter.YearMonthAttributeConverter;
import com.quocdoansam.schoolsystem.enums.SalaryStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Table(name = "salaries", uniqueConstraints = @UniqueConstraint(columnNames = { "teacher_id", "month_salary" }))
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

    @Column(nullable = false)
    BigDecimal baseAmount;

    @Builder.Default
    BigDecimal bonus = BigDecimal.ZERO;

    @Builder.Default
    BigDecimal deduction = BigDecimal.ZERO;

    @Column(nullable = false)
    BigDecimal totalAmount;

    @Column(nullable = false)
    SalaryStatus status;

    @Builder.Default
    String note = "No note yet";

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    Teacher teacher;

    @UpdateTimestamp
    LocalDateTime updatedAt;

    @CreationTimestamp
    LocalDateTime createdAt;
}
