package com.cun.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cun.entity.Student;

public interface StudentDao extends JpaRepository<Student, Integer> {
	@Query(value = "select * from t_student where department_id=?1", nativeQuery = true)
	List<Student> getStudentsByDepartmentId(Integer id);

}
