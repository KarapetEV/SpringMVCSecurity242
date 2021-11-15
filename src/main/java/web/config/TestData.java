package web.config;

import org.springframework.stereotype.Component;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class TestData {

    private final UserService userService;
    private final RoleService roleService;

    public TestData(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void insertData() {

        roleService.saveRole(new Role("ROLE_ADMIN"));
        roleService.saveRole(new Role("ROLE_USER"));

        Set<Role> setOfRoles = new HashSet<>();
        setOfRoles.add(roleService.getRoleByName("ROLE_USER"));

        User bob = new User();
        bob.setUsername("bob");
        bob.setPassword("bob");
        bob.setName("Bob");
        bob.setAge(23);
        bob.setEmail("bob23@test.com");
        bob.setRoles(setOfRoles);
        userService.addUser(bob);

        setOfRoles.add(roleService.getRoleByName("ROLE_ADMIN"));
        User tom = new User();
        tom.setUsername("tom");
        tom.setPassword("tom");
        tom.setName("Tom");
        tom.setAge(41);
        tom.setEmail("tom41@test.com");
        tom.setRoles(setOfRoles);
        userService.addUser(tom);
    }
}
