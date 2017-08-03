package by.epamlab.bugtracker.service.impl;

import by.epamlab.bugtracker.dao.intefaces.RoleDAO;
import by.epamlab.bugtracker.model.bean.Role;
import by.epamlab.bugtracker.service.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDAO roleDAO;

    @Override
    public Role getRoleById(int id) {
        return roleDAO.getRoleById(id);
    }

    @Override
    public List<Role> listRoles() {
        return roleDAO.listRoles();
    }
}
