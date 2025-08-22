package com.challenge.api.model;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class EmployeeImpl implements Employee {

    //variables are private for encapsulation
    private UUID uuid;
    private String firstName;
    private String lastName;
    private String fullName; // kept as a field but we also recompute when names change
    private Integer salary;
    private Integer age;
    private String jobTitle;
    private String email;
    private Instant contractHireDate;
    private Instant contractTerminationDate;

    public EmployeeImpl() {}

    public EmployeeImpl(
            UUID uuid,
            String firstName,
            String lastName,
            Integer salary,
            Integer age,
            String jobTitle,
            String email,
            Instant contractHireDate,
            Instant contractTerminationDate) {
        this.uuid = Objects.requireNonNull(uuid, "uuid"); //Primary key cant be null
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = buildFullName(firstName, lastName);
        this.salary = salary;
        this.age = age;
        this.jobTitle = jobTitle;
        this.email = email;
        this.contractHireDate = contractHireDate;
        this.contractTerminationDate = contractTerminationDate;
    }

    // Helper method to Handle cases where first or last name is missing.
    private static String buildFullName(String first, String last) {
        if ((first == null || first.isBlank()) && (last == null || last.isBlank())) return null;
        if (first == null || first.isBlank()) return last;
        if (last == null || last.isBlank()) return first;
        return first + " " + last;
    }

    @Override
    public UUID getUuid() {
        return uuid;
    }

    @Override
    public void setUuid(UUID uuid) {
        this.uuid = Objects.requireNonNull(uuid, "uuid");
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String name) {
        this.firstName = name;
        this.fullName = buildFullName(this.firstName, this.lastName);
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String name) {
        this.lastName = name;
    }

    @Override
    public String getFullName() {
        return fullName;
    }

    @Override
    public void setFullName(String name) {
        this.fullName = buildFullName(this.firstName, this.lastName);;
    }

    @Override
    public Integer getSalary() {
        return salary;
    }

    @Override
    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @Override
    public Integer getAge() {
        return age;
    }

    @Override
    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String getJobTitle() {
        return jobTitle;
    }

    @Override
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Instant getContractHireDate() {
        return contractHireDate;
    }

    @Override
    public void setContractHireDate(Instant date) {
        this.contractHireDate = date;
    }

    @Override
    public Instant getContractTerminationDate() {
        return contractTerminationDate;
    }

    @Override
    public void setContractTerminationDate(Instant date) {
        this.contractTerminationDate = date;
    }

    @Override public String toString() {
        return "Employee{uuid=" + uuid + ", fullName='" + getFullName() + "'}";
    }
}
