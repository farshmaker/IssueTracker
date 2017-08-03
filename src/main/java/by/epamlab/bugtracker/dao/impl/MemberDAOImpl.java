package by.epamlab.bugtracker.dao.impl;

import by.epamlab.bugtracker.dao.intefaces.MemberDAO;
import by.epamlab.bugtracker.model.bean.Employee;
import by.epamlab.bugtracker.model.bean.Member;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class MemberDAOImpl implements MemberDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addMember(Member member) {
        sessionFactory.getCurrentSession().save(member);
    }

    @Override
    public Member getMember(int id) {
        return (Member)sessionFactory.getCurrentSession().get(Member.class, id);
    }

    @Override
    public Member getMember(int projectId, int employeeId) {
        String hql = "FROM Member WHERE project.id = :project_id AND employee.id = :employee_id";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);

        query.setInteger("project_id", projectId);
        query.setInteger("employee_id", employeeId);

        return (Member) query.uniqueResult();
    }

    @Override
    public List<Member> listMember(Employee employee) {
        String hql = "FROM Member WHERE employee.id = :employee_id";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);

        query.setInteger("employee_id", employee.getId());

        return query.list();
    }

    @Override
    public List<Member> listMemberGroupByProject(Employee employee) {
        return null;
    }

    @Override
    public List<Member> listMember(int projectId) {
        String hql = "FROM Member WHERE project.id = :project_id";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);

        query.setInteger("project_id", projectId);

        return query.list();
    }

    @Override
    public List<Member> listMember() {
        return sessionFactory.getCurrentSession().createQuery("from Member").list();
    }

}
