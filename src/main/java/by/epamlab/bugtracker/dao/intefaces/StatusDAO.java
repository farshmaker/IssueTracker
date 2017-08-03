package by.epamlab.bugtracker.dao.intefaces;

import by.epamlab.bugtracker.model.bean.Status;

import java.util.List;

public interface StatusDAO {

    public Status getStatus(int id);

    public List<Status> listStatus();

    public Status getDefaultStatus();

}
