package by.epamlab.bugtracker.dao.impl;

import by.epamlab.bugtracker.dao.intefaces.ProjectDAO;
import by.epamlab.bugtracker.model.bean.Project;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class ProjectDAOImpl implements ProjectDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Project getProjectById(Integer id) {
        return (Project) sessionFactory.getCurrentSession().get(Project.class, id);
    }

    @Override
    public void addProject(Project project) {
        sessionFactory.getCurrentSession().save(project);
    }

    @Override
    public void updateProject(Project project) {
        sessionFactory.getCurrentSession().saveOrUpdate(project);
    }

    @Override
    public List<Project> listProject() {
        return sessionFactory.getCurrentSession().createQuery("from Project").list();
    }
}
