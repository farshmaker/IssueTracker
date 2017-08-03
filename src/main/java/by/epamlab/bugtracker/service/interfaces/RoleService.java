package by.epamlab.bugtracker.service.interfaces;

import by.epamlab.bugtracker.model.bean.Role;

import java.util.List;

public interface RoleService {

    public Role getRoleById(int id);

    public List<Role> listRoles();
}
