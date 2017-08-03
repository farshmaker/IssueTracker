package by.epamlab.bugtracker.service.interfaces;


import by.epamlab.bugtracker.model.bean.Attachment;

import java.util.List;

public interface AttachmentService {

    public Attachment getAttachment(int id);

    public void addAttachment(Attachment attachment);

    public List<Attachment> listAttachment(int taskId);

}
