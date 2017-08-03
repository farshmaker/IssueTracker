package by.epamlab.bugtracker.service.interfaces;

import by.epamlab.bugtracker.model.bean.Assigment;
import by.epamlab.bugtracker.model.bean.Project;
import by.epamlab.bugtracker.model.bean.Task;

import java.util.List;

public interface TaskService {

    public void addTask(Task task);

    public void addTask(Assigment assigment);

    public Task getTask(int id);

    public List<Task> listTask();

    public void updateTask(Task task);

    public Project getProject(Integer taskId);
}
