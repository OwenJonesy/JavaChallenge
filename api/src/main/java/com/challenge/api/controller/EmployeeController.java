package com.challenge.api.controller;

import com.challenge.api.model.Employee;
import com.challenge.api.model.EmployeeImpl;
import com.challenge.api.service.EmployeeService;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * REST controller for managing {@link Employee} resources.
 * <p>
 * This controller provides endpoints to:
 *  Retrieve all employees</li>
 *  Retrieve a single employee by their UUID</li>
 *  Create a new employee record</li>
 *
 */
@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Handles requests for retrieving all employees.
     *
     * @return ResponseEntity with a list of Employee objects and status 200 (OK).
     */
    @GetMapping // Tells spring it handles GET requests
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    /**
     * Retrieves a single employee by UUID.
     *
     * @param uuid the unique identifier of the employee
     * @return ResponseEntity with the Employee object and status 200 (OK),
     * or throws ResponseStatusException with 404 (Not Found) if employee does not exist.
     */
    @GetMapping("/{uuid}")
    public ResponseEntity<Employee> getEmployeeByUuid(@PathVariable UUID uuid) {
        Employee employee = employeeService.getEmployeeByUuid(uuid);
        if (employee == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
        }
        return ResponseEntity.ok(employee);
    }

    /**
     * Creates a new employee resource.
     *
     * @param employee request body containing employee details
     * @return ResponseEntity with the created Employee object, status 201 (Created),
     * and Location header pointing to the new resource URI.
     */
    @PostMapping // Tells spring to handle POST requests
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeImpl employee) {

        Employee createdEmployee = employeeService.createEmployee(employee);

        // builds a URI pointing to the newly created resource.
        URI location = ServletUriComponentsBuilder.fromCurrentRequest() // current request: /api/v1/employee
                .path("/{uuid}") // append uuid
                .buildAndExpand(createdEmployee.getUuid()) // replace {uuid} with actual UUID
                .toUri();

        // Return ResponseEntity with 201 Created, body, and Location header
        return ResponseEntity.created(location) // sets status 201 and Location header
                .body(createdEmployee); // include the created employee in response
    }
}
