package pe.edu.upeu.appsales.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.appsales.dao.accesstoken.AccessTokenDao;
import pe.edu.upeu.appsales.dao.user.UserDao;
import pe.edu.upeu.appsales.model.AccessToken;
import pe.edu.upeu.appsales.model.User;

import java.util.UUID;

/**
 * @author Fredy Tuco Calizaya
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    private AccessTokenDao accessTokenDao;

    protected UserServiceImpl() {
    }

    @Autowired
    public UserServiceImpl(UserDao userDao, AccessTokenDao accessTokenDao) {
        this.userDao = userDao;
        this.accessTokenDao = accessTokenDao;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userDao.loadUserByUsername(username);
    }

    @Override
    @Transactional
    public User findUserByAccessToken(String accessTokenString) {
        AccessToken accessToken = this.accessTokenDao.findByToken(accessTokenString);

        if (null == accessToken) {
            return null;
        }

        if (accessToken.isExpired()) {
            this.accessTokenDao.delete(accessToken);
            return null;
        }

        return accessToken.getUser();
    }

    @Override
    @Transactional
    public AccessToken createAccessToken(User user) {
        AccessToken accessToken = new AccessToken(user, UUID.randomUUID().toString());
        return this.accessTokenDao.save(accessToken);
    }
}
