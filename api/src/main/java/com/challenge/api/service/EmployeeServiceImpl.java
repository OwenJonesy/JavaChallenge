package com.challenge.api.service;

import com.challenge.api.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * In-memory implementation of EmployeeService.
 * This uses a thread-safe map so multiple requests won't corrupt state.
 */
@Service // Marks this as a Spring-managed service bean
public class EmployeeServiceImpl implements EmployeeService {


    // In-memory "database" - Using concurrent Hash Map in case multiple requests sent at once
    private final Map<UUID, Employee> employeeStore = new ConcurrentHashMap<>();

    @Override
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employeeStore.values());
    }

    @Override
    public Employee getEmployeeByUuid(UUID uuid) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        return employeeStore.get(uuid);
    }

    @Override
    public Employee createEmployee(Employee employee) {
        Objects.requireNonNull(employee, "employee must not be null");

        // Generate UUID if not already set
        if (employee.getUuid() == null) {
            employee.setUuid(UUID.randomUUID());
        }

        // Store in the map
        employeeStore.put(employee.getUuid(), employee);

        return employee;
    }
}
