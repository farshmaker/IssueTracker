package by.epamlab.bugtracker.service.impl;

import by.epamlab.bugtracker.dao.intefaces.AssigmentDAO;
import by.epamlab.bugtracker.dao.intefaces.StatusDAO;
import by.epamlab.bugtracker.dao.intefaces.TaskDAO;
import by.epamlab.bugtracker.model.bean.Assigment;
import by.epamlab.bugtracker.model.bean.Project;
import by.epamlab.bugtracker.model.bean.Status;
import by.epamlab.bugtracker.model.bean.Task;
import by.epamlab.bugtracker.service.interfaces.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDAO taskDAO;

    @Autowired
    private StatusDAO statusDAO;

    @Autowired
    private AssigmentDAO assigmentDAO;

    @Override
    public void addTask(Task task) {
        taskDAO.addTask(task);
    }

    @Override
    public void addTask(Assigment assigment) {
        Status status = statusDAO.getDefaultStatus();

        Task task = assigment.getTask();
        task.setStatus(status);

        taskDAO.addTask(task);
        assigmentDAO.addAssigment(assigment);
    }

    @Override
    public Task getTask(int id) {
        return taskDAO.getTask(id);
    }

    @Override
    public List<Task> listTask() {
        return taskDAO.listTask();
    }

    @Override
    public void updateTask(Task task) {
        taskDAO.updateTask(task);
    }

    @Override
    public Project getProject(Integer taskId) {
        return taskDAO.getProject(taskId);
    }


}
