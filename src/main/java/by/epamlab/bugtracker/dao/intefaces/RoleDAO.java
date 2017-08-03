package by.epamlab.bugtracker.dao.intefaces;

import by.epamlab.bugtracker.model.bean.Role;

import java.util.List;

public interface RoleDAO {

    public Role getRoleById(int id);

    public List<Role> listRoles();
}
