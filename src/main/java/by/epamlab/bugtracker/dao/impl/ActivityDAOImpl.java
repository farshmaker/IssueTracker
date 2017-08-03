package by.epamlab.bugtracker.dao.impl;

import by.epamlab.bugtracker.dao.intefaces.ActivityDAO;
import by.epamlab.bugtracker.model.bean.Activity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class ActivityDAOImpl implements ActivityDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addActivity(Activity activity) {
        sessionFactory.getCurrentSession().save(activity);
    }

    public Activity getActivity(int id) {
        return (Activity)sessionFactory.getCurrentSession().get(Activity.class, id);
    }

    public List<Activity> listActivity() {
        return sessionFactory.getCurrentSession().createQuery("from Activity").list();
    }

    public List<Activity> listActivity(int fromIndex, int toIndex) {
        String hql = "FROM Activity ORDER BY id DESC";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);

        query.setFirstResult(fromIndex);
        query.setMaxResults(toIndex);

        return query.list();
    }
}
