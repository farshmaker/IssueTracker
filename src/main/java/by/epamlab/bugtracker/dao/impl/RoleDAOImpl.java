package by.epamlab.bugtracker.dao.impl;

import by.epamlab.bugtracker.dao.intefaces.RoleDAO;
import by.epamlab.bugtracker.model.bean.Role;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class RoleDAOImpl implements RoleDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Role getRoleById(int id) {
        return (Role)sessionFactory.getCurrentSession().get(Role.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Role> listRoles() {
        return sessionFactory.getCurrentSession().createQuery("from Role").list();
    }
}
