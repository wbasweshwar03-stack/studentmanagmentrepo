package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Employee;
import com.example.demo.Repository.EmployeeRepo;

@RestController
public class controller {

	@Autowired
	EmployeeRepo employee;

	@GetMapping("getAllEmployee")
	public List<Employee> getEmployee() {
		List<Employee> emp = employee.findAll();
		return emp;

	}

	@GetMapping("getEmployeeById")
	public Employee getEmployeeById(@RequestHeader int id) {
		Employee emp = employee.findById(id).get();
		return emp;

	}

	@GetMapping("getAllEmployeeByAscendingSalary")
	public List<Employee> getEmployeeBysort() {
		List<Employee> emp = employee.findAll(Sort.by("Salary").ascending());
		return emp;

	}

	@GetMapping("getAllEmployeeByDescendingSalary")
	public List<Employee> getEmployeeBysorting() {
		List<Employee> emp = employee.findAll(Sort.by("Salary").descending());
		return emp;

	}

	@GetMapping("getAllEmployeeByDesignation")
	public List<Employee> getEmployeeByDesignation() {
		List<Employee> emp = employee.findAll(Sort.by("designation"));
		return emp;

	}

	@GetMapping("getAllEmployeeById/id")
	public List<Employee> getEmployeesById(@PathVariable int a) {
		List<Employee> emp = employee.findAllById(List.of(a));
		return emp;

	}

	@PostMapping("addEmployee")
	public String addEmployee(@RequestBody Employee emp) {
		employee.save(emp);
		return "Employee Added Successfuly";
	}

	@PostMapping("addEmployeeInBulk")
	public List<Employee> saveAllEmployee(@RequestBody List<Employee> emp) {
		List<Employee> savedEmployee = employee.saveAll(emp);
		return savedEmployee;
	}

	@PutMapping("updateMultipleEmployee/{id}/{id}")
	public void updateMultipleEmployee(@RequestBody @PathVariable List<Employee> emp, int a, int b) {
		List<Employee> employees = employee.findAllById(List.of(a, b));
		employees.forEach(e -> e.setFirstname(e.getFirstname()));
		employees.forEach(e -> e.setLastname(e.getLastname()));
		employees.forEach(e -> e.setSalary(e.getSalary()));
		employees.forEach(e -> e.setDesignation(e.getDesignation()));
		employee.saveAll(employees);

	}

	@PutMapping("updateEmployee/{id}")
	public String updateEmployee(@PathVariable @RequestBody int id, Employee emp) {
		Employee empobject = employee.findById(id).get();
		empobject.setFirstname(emp.getFirstname());
		empobject.setLastname(emp.getLastname());
		empobject.setSalary(emp.getSalary());
		empobject.setDesignation(emp.getDesignation());
		return "Employee Updated!";
	}

	@DeleteMapping("deleteById/{id}")
	public String deleteEmployee(@PathVariable int id) {
		employee.deleteById(id);
		return "Employee deleted successfully";
	}

	
}
