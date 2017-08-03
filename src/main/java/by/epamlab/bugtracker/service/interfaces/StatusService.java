package by.epamlab.bugtracker.service.interfaces;

import by.epamlab.bugtracker.model.bean.Status;

import java.util.List;

public interface StatusService {

    public Status getStatus(int id);

    public List<Status> listStatus();

    public Status getDefaultStatus();
}
