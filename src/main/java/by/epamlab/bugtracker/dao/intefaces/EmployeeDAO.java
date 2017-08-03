package by.epamlab.bugtracker.dao.intefaces;

import by.epamlab.bugtracker.model.bean.Employee;

import java.util.List;

public interface EmployeeDAO {

    public void addEmployee(Employee employee);

    public void updateEmployee(Employee employee);

    public void deleteEmployee(Employee employee);

    public Employee getEmployee(String login);

    public Employee getEmployee(Integer id);

    public List<Employee> listEmployee();
}
