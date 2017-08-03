package by.epamlab.bugtracker.web.controller;

import by.epamlab.bugtracker.model.bean.*;
import by.epamlab.bugtracker.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalAttributeControllerAdvice {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private MemberService memberService;

    @ModelAttribute("employees")
    public List<Employee> initializeEmployees() {

        return employeeService.listEmployee();
    }

    @ModelAttribute("positions")
    public List<Position> initializePositions() {

        return positionService.listPosition();
    }

    @ModelAttribute("projects")
    public List<Project> initializeProjects() {

        return projectService.listProject();
    }

    @ModelAttribute("current_projects")
    public List<Member> initializeCurrentProjects() {

        List<Member> emptyList = new ArrayList<Member>();

        if(!("none".equals(getLoggedEmployee()))) {

            return memberService.listMember(getLoggedEmployee());
        }
        return emptyList;
    }

    @ModelAttribute("roles")
    public List<Role> initializeRoles(){
        return roleService.listRoles();
    }

    @ModelAttribute("activities")
    public List<Activity> initializeActivities(){
        return activityService.listActivity();
    }

    @ModelAttribute("employee")
    public Employee emptyEmployee(){
        return new Employee();
    }

    @ModelAttribute("project")
    public Project emptyProject(){
        return new Project();
    }

    @ModelAttribute("member")
    public Member emptyMember(){
        return new Member();
    }

    @ModelAttribute("task")
    public Task emptyTask(){
        return new Task();
    }

    @ModelAttribute("activity")
    public Activity emptyActivity(){
        return new Activity();
    }

    @ModelAttribute("assigment")
    public Assigment emptyAssigment(){
        return new Assigment();
    }

    @ModelAttribute("attachment")
    public Attachment emptyAttachment(){
        return new Attachment();
    }

    @ModelAttribute("loggedEmployee")
    public String getLoggedEmployee() {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        Employee employee = employeeService.getEmployee(login);
        return (employee != null) ? employee.getLogin() : "none";
    }

}
