package com.challenge.api.service;

import com.challenge.api.model.Employee;
import java.util.List;
import java.util.UUID;

// Service layer interface for handling operations for Employees
public interface EmployeeService {

    /**
     * Get all employees.
     * @return list of employees, never null (possibly empty).
     * list is used as is maps naturally to JSON
     * Returning List<Employee> instead of a concrete type
     * (like ArrayList<Employee>) gives flexibility allowing for implementation
     * change without affecting contract.
     */
    List<Employee> getAllEmployees();

    /**
     * Find a single employee by their UUID.
     * @param uuid required non-null
     * @return the employee if found, or null/exception if not found
     */
    Employee getEmployeeByUuid(UUID uuid);

    /**
     * Create and store a new employee.
     * @param employee employee to be created
     * @return the created employee (with UUID set)
     */
    Employee createEmployee(Employee employee);
}
