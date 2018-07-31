package com.cun.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cun.entity.Department;

public interface DepartmentDao extends JpaRepository<Department, Integer> {

}
