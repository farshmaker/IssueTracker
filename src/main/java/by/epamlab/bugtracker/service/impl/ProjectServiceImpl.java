package by.epamlab.bugtracker.service.impl;

import by.epamlab.bugtracker.dao.intefaces.ProjectDAO;
import by.epamlab.bugtracker.model.bean.Project;
import by.epamlab.bugtracker.service.interfaces.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectDAO projectDAO;

    @Override
    public Project getProjectById(Integer id) {
        return projectDAO.getProjectById(id);
    }

    @Override
    public void addProject(Project project) {
        projectDAO.addProject(project);
    }

    @Override
    public void updateProject(Project project) {
        projectDAO.updateProject(project);
    }

    @Override
    public List<Project> listProject() {
        return projectDAO.listProject();
    }
}
