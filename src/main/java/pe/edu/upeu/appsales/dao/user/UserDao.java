package pe.edu.upeu.appsales.dao.user;


import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pe.edu.upeu.appsales.dao.Dao;
import pe.edu.upeu.appsales.model.User;

public interface UserDao extends Dao<User, Long> {
    User loadUserByUsername(String username) throws UsernameNotFoundException;

    User findByName(String name);
}
