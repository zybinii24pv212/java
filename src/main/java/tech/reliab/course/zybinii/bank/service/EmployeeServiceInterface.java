package tech.reliab.course.zybinii.bank.service;

import tech.reliab.course.zybinii.bank.entity.Employee;

import java.util.List;

public interface EmployeeServiceInterface {

    Employee create(Employee employee);

    Employee read(Long id);

    Employee update(Employee employee);

    boolean delete(Long id);

    List<Employee> getAllEmployees();
}
