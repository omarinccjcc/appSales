package pe.edu.upeu.appsales.dao.accesstoken;


import pe.edu.upeu.appsales.dao.Dao;
import pe.edu.upeu.appsales.model.AccessToken;

public interface AccessTokenDao extends Dao<AccessToken, Long> {
    AccessToken findByToken(String accessTokenString);
}
