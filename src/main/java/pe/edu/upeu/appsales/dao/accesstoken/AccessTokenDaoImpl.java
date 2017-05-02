package pe.edu.upeu.appsales.dao.accesstoken;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.appsales.dao.JpaDao;
import pe.edu.upeu.appsales.model.AccessToken;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository("accessTokenDao")
public class AccessTokenDaoImpl extends JpaDao<AccessToken, Long> implements AccessTokenDao {

    public AccessTokenDaoImpl() {
        super(AccessToken.class);
    }

    @Override
    @Transactional(readOnly = true, noRollbackFor = NoResultException.class)
    public AccessToken findByToken(String accessTokenString) {
        CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<AccessToken> query = builder.createQuery(this.entityClass);
        Root<AccessToken> root = query.from(this.entityClass);
        query.where(builder.equal(root.get("token"), accessTokenString));
        try {
            return this.getEntityManager().createQuery(query).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
