package by.epamlab.bugtracker.dao.impl;

import by.epamlab.bugtracker.dao.intefaces.TaskDAO;
import by.epamlab.bugtracker.model.bean.Project;
import by.epamlab.bugtracker.model.bean.Task;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class TaskDAOImpl implements TaskDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addTask(Task task) {
        sessionFactory.getCurrentSession().save(task);
    }

    @Override
    public Task getTask(int id) {
        return (Task) sessionFactory.getCurrentSession().get(Task.class, id);
    }

    @Override
    public List<Task> listTask() {
        return sessionFactory.getCurrentSession().createQuery("from Task").list();
    }

    @Override
    public void updateTask(Task task) {
        sessionFactory.getCurrentSession().update(task);
    }

    @Override
    public Project getProject(Integer taskId) {
        String hql = "select project from Task T where T.id = :taskId ";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);

        query.setParameter("taskId", taskId);

        return (Project) query.uniqueResult();
    }


}
