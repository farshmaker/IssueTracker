package by.epamlab.bugtracker.service.interfaces;

import by.epamlab.bugtracker.model.bean.Assigment;
import by.epamlab.bugtracker.model.bean.Member;
import by.epamlab.bugtracker.model.bean.Task;

import java.util.List;

public interface AssigmentService {

    public void addAssigment(Assigment assignment);

    public Assigment getAssigment(int taskId);

    public List<Assigment> listAssigment();

    public List<Assigment> listAssigment(Member member);

    public void updateAssigment(Assigment assigment);

    List<Task> getAssignedTasks(String login);
}
