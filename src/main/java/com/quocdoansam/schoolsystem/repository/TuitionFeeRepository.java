package com.quocdoansam.schoolsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quocdoansam.schoolsystem.entity.TuitionFee;

@Repository
public interface TuitionFeeRepository extends JpaRepository<TuitionFee, String> {

}
