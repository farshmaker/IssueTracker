package by.epamlab.bugtracker.service.interfaces;

import by.epamlab.bugtracker.model.bean.Project;

import java.util.List;

public interface ProjectService {

    public Project getProjectById(Integer id);

    public void addProject(Project project);

    public void updateProject(Project project);

    public List<Project> listProject();
}
