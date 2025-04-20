package com.quocdoansam.schoolsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quocdoansam.schoolsystem.entity.Major;

@Repository
public interface MajorRepository extends JpaRepository<Major, String> {

}
