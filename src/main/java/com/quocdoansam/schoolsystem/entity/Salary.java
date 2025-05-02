package com.quocdoansam.schoolsystem.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.quocdoansam.schoolsystem.converter.YearMonthAttributeConverter;
import com.quocdoansam.schoolsystem.enums.SalaryStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
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
@EntityListeners(AuditingEntityListener.class)
public class Salary {
    @SuppressWarnings("deprecation")
    @Id
    @GeneratedValue(generator = "salary-id-generator")
    @GenericGenerator(name = "salary-id-generator", strategy = "com.quocdoansam.schoolsystem.util.SalaryIdGenerator")
    String id;

    @Convert(converter = YearMonthAttributeConverter.class)
    YearMonth monthSalary;

    @Column(nullable = false)
    BigDecimal baseAmount;

    BigDecimal bonus;
    BigDecimal deduction;

    @Column(nullable = false)
    BigDecimal totalAmount;

    @Column(nullable = false)
    SalaryStatus status;

    String note;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    Teacher teacher;

    @CreatedBy
    String createdBy;

    @LastModifiedBy
    String updatedBy;

    @UpdateTimestamp
    LocalDateTime updatedAt;

    @CreationTimestamp
    LocalDateTime createdAt;
}
