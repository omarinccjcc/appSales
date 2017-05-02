package pe.edu.upeu.appsales.dao;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pe.edu.upeu.appsales.dao.user.UserDao;
import pe.edu.upeu.appsales.model.Role;
import pe.edu.upeu.appsales.model.User;

/**
 * Initialize the database with some test entries.
 *
 * @author Fredy Tuco Calizaya
 */

public class DataBaseInitializer {

    private UserDao userDao;

    private PasswordEncoder passwordEncoder;

    protected DataBaseInitializer() {
        /* Default constructor for reflection instantiation */
    }

    public DataBaseInitializer(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    public void initDataBase() {
        User userUser = new User("user", this.passwordEncoder.encode("user"));
        userUser.addRole(Role.USER);
        this.userDao.save(userUser);

        User adminUser = new User("admin", this.passwordEncoder.encode("admin"));
        adminUser.addRole(Role.USER);
        adminUser.addRole(Role.ADMIN);
        this.userDao.save(adminUser);

    }
}
