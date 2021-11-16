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


    public void insertData() {

        roleService.saveRole(new Role("ROLE_ADMIN"));
        roleService.saveRole(new Role("ROLE_USER"));

        Set<Role> roles1 = new HashSet<>();
        roles1.add(new Role("ROLE_USER"));

        User bob = new User("bob", "bob", "Bob", 23, "bob23@test.com");
//        bob.setUsername("bob");
//        bob.setPassword("bob");
//        bob.setName("Bob");
//        bob.setAge(23);
//        bob.setEmail("bob23@test.com");
//        bob.setRoles(setOfRoles);
        bob.setRoles(roles1);
        userService.addUser(bob);

        Set<Role> roles2 = new HashSet<>();
        roles2.add(new Role("ROLE_USER"));
        roles2.add(new Role("ROLE_ADMIN"));

        User tom = new User("tom", "tom", "Tom", 38, "tom38@test.com");
//        tom.setUsername("tom");
//        tom.setPassword("tom");
//        tom.setName("Tom");
//        tom.setAge(41);
//        tom.setEmail("tom41@test.com");
//        tom.setRoles(setOfRoles);
        tom.setRoles(roles2);
        userService.addUser(tom);
    }
}
