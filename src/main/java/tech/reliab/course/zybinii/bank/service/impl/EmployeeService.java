package tech.reliab.course.zybinii.bank.service.impl;

import tech.reliab.course.zybinii.bank.entity.Employee;
import tech.reliab.course.zybinii.bank.service.EmployeeServiceInterface;

import java.util.ArrayList;
import java.util.List;

public class EmployeeService implements EmployeeServiceInterface {
    private final BankService bankService;

    private final List<Employee> employeeStorage = new ArrayList<>();
    private Long idCounter = 1L;

    public EmployeeService(BankService bankService) {
        this.bankService = bankService;
    }

    @Override
    public Employee create(Employee employee) {
        employee.setId(idCounter++);
        employeeStorage.add(employee);
        bankService.addEmployee(employee.getBank().getId());
        return employee;
    }

    @Override
    public Employee read(Long id) {
        for (Employee employee : employeeStorage) {
            if (employee.getId().equals(id)) {
                return employee;
            }
        }
        return null;
    }

    @Override
    public Employee update(Employee employee) {
        for (int i = 0; i < employeeStorage.size(); i++) {
            if (employeeStorage.get(i).getId().equals(employee.getId())) {
                employeeStorage.set(i, employee);
                return employee;
            }
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return employeeStorage.removeIf(employee -> employee.getId().equals(id));
    }

    @Override
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employeeStorage);
    }

}
