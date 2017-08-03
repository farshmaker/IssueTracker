package by.epamlab.bugtracker.service.impl;

import by.epamlab.bugtracker.model.bean.Employee;
import by.epamlab.bugtracker.dao.intefaces.EmployeeDAO;
import by.epamlab.bugtracker.service.interfaces.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;


    public void addEmployee(Employee employee) {
        employeeDAO.addEmployee(employee);
    }

    public void updateEmployee(Employee employee){
        employeeDAO.updateEmployee(employee);
    }

    public void deleteEmployee(Employee employee){
        employeeDAO.deleteEmployee(employee);
    }

    public Employee getEmployee(String login){
        return employeeDAO.getEmployee(login);
    }

    public Employee getEmployee(Integer id){
        return employeeDAO.getEmployee(id);
    }

    public List<Employee> listEmployee() {
        return employeeDAO.listEmployee();
    }
}
