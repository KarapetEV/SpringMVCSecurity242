package web.service;

import web.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {

    List<Role> getAllRoles();

    Role getRoleById(long id);

    Role getRoleByName(String roleName);

    void saveRole(Role role);

    void updateRole(Role role);

    void removeRole(long id);

    Set<Role> getSetOfRoles(List<String> rolesId);
}
