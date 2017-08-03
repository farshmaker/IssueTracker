package by.epamlab.bugtracker.dao.impl;

import by.epamlab.bugtracker.model.bean.Employee;
import by.epamlab.bugtracker.dao.intefaces.EmployeeDAO;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class EmployeeDAOImpl implements EmployeeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addEmployee(Employee employee) {
        sessionFactory.getCurrentSession().save(employee);

    }

    @Override
    public void updateEmployee(Employee employee){
        sessionFactory.getCurrentSession().saveOrUpdate(employee);
    }

    @Override
    public void deleteEmployee(Employee employee){
        sessionFactory.getCurrentSession().delete(employee);
    }

    @Override
    public Employee getEmployee(String login){
        String hql = "from Employee E where E.login = :login";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);

        query.setParameter("login", login);

        return (Employee) query.uniqueResult();

    }

    @Override
    public Employee getEmployee(Integer id){
        return (Employee)sessionFactory.getCurrentSession().get(Employee.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Employee> listEmployee() {
        return sessionFactory.getCurrentSession().createQuery("from Employee").list();
    }
}
