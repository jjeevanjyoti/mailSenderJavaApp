package com.assignment.springboot.mongo.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.springboot.mongo.dao.Employeedao;
import com.assignment.springboot.mongo.model.Employee;

@Service
public class Employeeservimpl implements Employeeservice {

	@Autowired
	Employeedao dao;

	
	public void createEmployee(List<Employee> emp) {
		dao.saveAll(emp);
	}

	
}