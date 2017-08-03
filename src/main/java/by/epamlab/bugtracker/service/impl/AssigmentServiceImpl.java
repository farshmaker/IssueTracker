package by.epamlab.bugtracker.service.impl;

import by.epamlab.bugtracker.dao.intefaces.AssigmentDAO;
import by.epamlab.bugtracker.dao.intefaces.EmployeeDAO;
import by.epamlab.bugtracker.dao.intefaces.MemberDAO;
import by.epamlab.bugtracker.dao.intefaces.TaskDAO;
import by.epamlab.bugtracker.model.bean.Assigment;
import by.epamlab.bugtracker.model.bean.Employee;
import by.epamlab.bugtracker.model.bean.Member;
import by.epamlab.bugtracker.model.bean.Task;
import by.epamlab.bugtracker.service.interfaces.AssigmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class AssigmentServiceImpl implements AssigmentService {

    @Autowired
    private AssigmentDAO assigmentDAO;

    @Autowired
    private EmployeeDAO employeeDAO;

    @Autowired
    private MemberDAO memberDAO;

    @Autowired
    private TaskDAO taskDAO;

    @Override
    public void addAssigment(Assigment assignment) {
        assigmentDAO.addAssigment(assignment);
    }

    @Override
    public Assigment getAssigment(int taskId) {
        return assigmentDAO.getAssigment(taskId);
    }

    @Override
    public List<Assigment> listAssigment() {
        return assigmentDAO.listAssigment();
    }

    @Override
    public List<Assigment> listAssigment(Member member) {
        return assigmentDAO.listAssigment(member);
    }

    @Override
    public void updateAssigment(Assigment assigment) {
        assigmentDAO.updateAssigment(assigment);
    }

    @Override
    public List<Task> getAssignedTasks(String login) {
        Employee employee = employeeDAO.getEmployee(login);
        List<Member> members = memberDAO.listMember(employee);

        List<Assigment> assignments = new ArrayList<>();
        for (Member member : members) {
            assignments.addAll(assigmentDAO.listAssigment(member));
        }

        List<Task> tasks = new ArrayList<>();
        for (Assigment assignment : assignments) {
            int taskId = assignment.getTask().getId();
            tasks.add(taskDAO.getTask(taskId));
        }

        return tasks;
    }
}
