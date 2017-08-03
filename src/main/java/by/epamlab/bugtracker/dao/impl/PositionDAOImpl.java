package by.epamlab.bugtracker.dao.impl;

import by.epamlab.bugtracker.model.bean.Position;
import by.epamlab.bugtracker.dao.intefaces.PositionDAO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class PositionDAOImpl implements PositionDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void addPosition(Position position) {
        sessionFactory.getCurrentSession().save(position);
    }

    public Position getById(Integer id){
        return (Position)sessionFactory.getCurrentSession().get(Position.class, id);
    }

    public List<Position> listPosition() {
        return sessionFactory.getCurrentSession().createQuery("from Position ").list();
    }

    public void removePosition(Integer id) {
        Position position = (Position) sessionFactory.getCurrentSession().load(
                Position.class, id);
        if (null != position){
            sessionFactory.getCurrentSession().delete(position);
        }

    }
}
