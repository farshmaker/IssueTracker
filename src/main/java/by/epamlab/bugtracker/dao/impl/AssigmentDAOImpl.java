package by.epamlab.bugtracker.dao.impl;

import by.epamlab.bugtracker.dao.intefaces.AssigmentDAO;
import by.epamlab.bugtracker.model.bean.Assigment;
import by.epamlab.bugtracker.model.bean.Member;
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
public class AssigmentDAOImpl implements AssigmentDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addAssigment(Assigment assignment) {
        sessionFactory.getCurrentSession().saveOrUpdate(assignment);
    }

    @Override
    public Assigment getAssigment(int taskId) {
        String hql = "FROM Assigment WHERE task.id = :task_id";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);

        query.setInteger("task_id", taskId);

        return (Assigment) query.uniqueResult();
    }

    @Override
    public List<Assigment> listAssigment() {
        return sessionFactory.getCurrentSession().createQuery("from Assigment").list();
    }

    @Override
    public List<Assigment> listAssigment(Member member) {
        String hql = "FROM Assigment A WHERE A.member.id = :member_id";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);

        query.setInteger("member_id", member.getId());

        return query.list();
    }

    @Override
    public void updateAssigment(Assigment assigment) {
        sessionFactory.getCurrentSession().update(assigment);
    }
}
