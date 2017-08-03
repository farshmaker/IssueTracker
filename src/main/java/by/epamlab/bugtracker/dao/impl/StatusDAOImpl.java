package by.epamlab.bugtracker.dao.impl;

import by.epamlab.bugtracker.dao.intefaces.StatusDAO;
import by.epamlab.bugtracker.model.bean.Status;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class StatusDAOImpl implements StatusDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Status getStatus(int id) {
        return (Status) sessionFactory.getCurrentSession().get(Status.class, id);
    }

    @Override
    public List<Status> listStatus() {
        return sessionFactory.getCurrentSession().createQuery("from Status ").list();
    }

    @Override
    public Status getDefaultStatus() {
        final int TODO_STATUS_ID = 1;
        return getStatus(TODO_STATUS_ID);
    }
}
