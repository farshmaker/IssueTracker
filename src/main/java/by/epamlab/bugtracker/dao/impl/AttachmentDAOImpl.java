package by.epamlab.bugtracker.dao.impl;

import by.epamlab.bugtracker.dao.intefaces.AttachmentDAO;
import by.epamlab.bugtracker.model.bean.Attachment;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class AttachmentDAOImpl implements AttachmentDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Attachment getAttachment(int id) {
        return (Attachment)sessionFactory.getCurrentSession().get(Attachment.class, id);
    }

    @Override
    public List<Attachment> listAttachment(int taskId) {
        String hql = "from Attachment A where A.task.id =:taskId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);

        query.setParameter("taskId", taskId);

        return query.list();
    }

    @Override
    public void addAttachment(Attachment attachment) {
        sessionFactory.getCurrentSession().save(attachment);
    }
}
