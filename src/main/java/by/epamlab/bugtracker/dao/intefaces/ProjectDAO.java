package by.epamlab.bugtracker.dao.intefaces;

import by.epamlab.bugtracker.model.bean.Project;

import java.util.List;

public interface ProjectDAO {

    public Project getProjectById(Integer id);

    public void addProject(Project project);

    public void updateProject(Project project);

    public List listProject();
}
