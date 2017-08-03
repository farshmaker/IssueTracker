package by.epamlab.bugtracker.service.impl;

import by.epamlab.bugtracker.dao.intefaces.StatusDAO;
import by.epamlab.bugtracker.model.bean.Status;
import by.epamlab.bugtracker.service.interfaces.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusDAO statusDAO;

    @Override
    public Status getStatus(int id) {
        return statusDAO.getStatus(id);
    }

    @Override
    public List<Status> listStatus() {
        return statusDAO.listStatus();
    }

    @Override
    public Status getDefaultStatus() {
        return statusDAO.getDefaultStatus();
    }
}
