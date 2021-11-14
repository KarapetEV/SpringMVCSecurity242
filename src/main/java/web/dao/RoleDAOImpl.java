package web.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import web.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Repository
public class RoleDAOImpl implements RoleDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("from Role", Role.class).getResultList();
    }

    @Override
    public Role getRoleById(long id) {
        return (Role) entityManager.createQuery("from Role r where r.id=:id")
                .setParameter("id", id).getSingleResult();
    }

    @Override
    public Role getRoleByName(String roleName) {

        return (Role) entityManager.createQuery("from Role r where r.role=:role")
                .setParameter("role", roleName)
                .getSingleResult();
    }

    @Override
    public void saveRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public void updateRole(Role role) {
        entityManager.merge(role);
    }

    @Override
    public void removeRole(long id) {
        entityManager.remove(getRoleById(id));
    }

    public Set<Role> getSetOfRoles(List<String> rolesId) {
        Set<Role> setOfRoles = new HashSet<>();
        for (String id : rolesId) {
            setOfRoles.add(getRoleById(Long.parseLong(id)));
        }
        return setOfRoles;
    }
}
