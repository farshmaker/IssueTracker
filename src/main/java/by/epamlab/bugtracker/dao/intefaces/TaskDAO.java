package by.epamlab.bugtracker.dao.intefaces;

import by.epamlab.bugtracker.model.bean.Project;
import by.epamlab.bugtracker.model.bean.Task;

import java.util.List;

public interface TaskDAO {

    public void addTask(Task task);

    public Task getTask(int id);

    public List<Task> listTask();

    public void updateTask(Task task);

    public Project getProject(Integer taskId);

}
