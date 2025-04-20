package com.quocdoansam.schoolsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quocdoansam.schoolsystem.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

}
