package pe.edu.upeu.appsales.dao.cliente;

import pe.edu.upeu.appsales.dao.Dao;
import pe.edu.upeu.appsales.dao.JpaDao;
import pe.edu.upeu.appsales.model.Cliente;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


/**
 * Created by Eduardo on 25/04/2017.
 */
public class ClienteDaoImpl extends JpaDao<Cliente, Long> implements ClienteDao {

    public  ClienteDaoImpl(){
        super(Cliente.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        final CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<Cliente> criteriaQuery = builder.createQuery(Cliente.class);

        Root<Cliente> root = criteriaQuery.from(Cliente.class);
        criteriaQuery.orderBy(builder.desc(root.get("razonSocialNombre")));

        TypedQuery<Cliente> typedQuery = this.getEntityManager().createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }


}
