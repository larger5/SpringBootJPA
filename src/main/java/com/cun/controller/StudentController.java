package com.cun.controller;

import java.util.ArrayList;
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
 * 主 Student CRUD
 * ==>和单表的 CRUD 异同：
 * 		① insert（同）
 * 		② update（同）
 * 		③ delete（同）
 * 		④ select（异）：关联查询
 * @author linhongcun
 *
 */
@RestController
@RequestMapping("/student")
@EnableSwagger2
public class StudentController {

	@Autowired
	private StudentDao studentDao;

	@Autowired
	private DepartmentDao departmentDao;

	/**
	 * 1、增
	 * ①学生姓名
	 * ②宿舍 id 
	 * @param student
	 */
	@PostMapping("/insert")
	public void insertStudent(Student student) {
		studentDao.save(student);
	}

	/**
	 * 2、根据id获取一个学生信息（个人、宿舍）
	 * @param id
	 * @return
	 */
	@GetMapping("/get/{id}")
	public Map<String, Object> getStudent(@PathVariable Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Student student = studentDao.findOne(id);
		// 这一步很关键
		Department department = departmentDao.findOne(student.getDepartmentId());
		map.put("student", student);
		map.put("department", department);
		return map;
	}

	/**
	 * 3、删
	 * @param id
	 */
	@DeleteMapping("/delete/{id}")
	public void deleteStudentById(@PathVariable Integer id) {
		studentDao.delete(id);
	}

	/**
	 * 4、改
	 * @param student
	 */
	@PutMapping("/update")
	public void updateStudent(Student student) {
		studentDao.save(student);
	}

	/**
	 * 5、全
	 * @return
	 */
	@GetMapping("/all")
	public List<Map<String, Object>> getAllStudent() {
		List<Student> studentList = studentDao.findAll();
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		for (int i = 0; i < studentList.size(); i++) {
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("student", studentList.get(i));
			map1.put("department", departmentDao.findOne(studentList.get(i).getDepartmentId()));
			list.add(map1);
		}
		return list;
	}

}
