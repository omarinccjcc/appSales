package pe.edu.upeu.appsales.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import pe.edu.upeu.appsales.model.AccessToken;
import pe.edu.upeu.appsales.model.User;

/**
 * @author Fredy Tuco Calizaya
 */
public interface UserService extends UserDetailsService {
    User findUserByAccessToken(String accessToken);

    AccessToken createAccessToken(User user);
}
