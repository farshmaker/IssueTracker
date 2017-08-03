package by.epamlab.bugtracker.service.impl;

import by.epamlab.bugtracker.model.bean.Employee;
import by.epamlab.bugtracker.model.enums.EPosition;
import by.epamlab.bugtracker.service.interfaces.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    EmployeeService employeeService;

    public UserDetails loadUserByUsername(String j_username) throws UsernameNotFoundException {
        Employee employee = employeeService.getEmployee(j_username);
        if (employee == null) {
            throw new UsernameNotFoundException("Employee with the login " + j_username + " not found!");
        }

        int roleIndex = employee.getPosition().getId() - 1;
        String[] roles = EPosition.values()[roleIndex].getRole();

        Set<SimpleGrantedAuthority> userRoles = new HashSet<>();
        for (String role : roles) {
            userRoles.add(new SimpleGrantedAuthority(role));
        }
        User user = new User(j_username, employee.getPassword(), userRoles);
        return user;
    }

}
