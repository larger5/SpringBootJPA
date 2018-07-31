package com.cun.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cun.dao.DepartmentDao;
import com.cun.dao.StudentDao;
import com.cun.entity.Department;
import com.cun.entity.Student;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 副 Department CRUD
 * ==>和单表简单的 CURD 的异同：
 * 		① delete（异）：有 id 作为 Student 的 departmentId 者不能删
 * 		② insert（同）
 * 		③ select（同）
 * 		④ update（同）
 * @author linhongcun
 *
 */
@EnableSwagger2
@RestController
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	private StudentDao studentDao;

	@Autowired
	private DepartmentDao departmentDao;

	/**
	 * 1、增
	 * @param department
	 */
	@PostMapping("/insert")
	public void insertDepartment(Department department) {
		departmentDao.save(department);
	}

	/**
	 * 2、改
	 * @param department
	 */
	@PutMapping("/update")
	public void updateDepartment(Department department) {
		departmentDao.save(department);
	}

	/**
	 * 3、删（优化）
	 * @param id
	 * @return 
	 */
	@DeleteMapping("/delete/{id}")
	public Map<String, Object> deleteDepartment(@PathVariable Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 以下两步很关键
		List<Student> studentsByDepartmentId = studentDao.getStudentsByDepartmentId(id);
		if (studentsByDepartmentId.size() == 0) {
			departmentDao.delete(id);
			map.put("status", true);
		} else {
			map.put("status", false);
		}
		return map;
	}

	/**
	 * 4、全
	 * @return
	 */
	@GetMapping("/all")
	public List<Department> getAllDepartment() {
		return departmentDao.findAll();
	}

	/**
	 * 5、查
	 * @param id
	 * @return
	 */
	@GetMapping("/get/{id}")
	public Department getDepartmentById(@PathVariable Integer id) {
		return departmentDao.findOne(id);
	}
}