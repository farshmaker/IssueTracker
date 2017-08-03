package by.epamlab.bugtracker.dao.intefaces;


import by.epamlab.bugtracker.model.bean.Attachment;

import java.util.List;

public interface AttachmentDAO {

    public Attachment getAttachment(int id);

    public List<Attachment> listAttachment(int taskId);

    public void addAttachment(Attachment attachment);
}
