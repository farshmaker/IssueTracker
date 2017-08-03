package by.epamlab.bugtracker.service.impl;

import by.epamlab.bugtracker.dao.intefaces.AttachmentDAO;
import by.epamlab.bugtracker.model.bean.Attachment;
import by.epamlab.bugtracker.service.interfaces.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    private AttachmentDAO attachmentDAO;

    @Override
    public Attachment getAttachment(int id) {
        return attachmentDAO.getAttachment(id);
    }

    @Override
    public void addAttachment(Attachment attachment) {
        attachmentDAO.addAttachment(attachment);
    }

    @Override
    public List<Attachment> listAttachment(int taskId) {
        return attachmentDAO.listAttachment(taskId);
    }
}
