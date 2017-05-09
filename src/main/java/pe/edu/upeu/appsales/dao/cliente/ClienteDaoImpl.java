package pe.edu.upeu.appsales.dao.cliente;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import pe.edu.upeu.appsales.dao.JpaDao;
import pe.edu.upeu.appsales.model.Cliente;


/**
 * Created by Eduardo on 25/04/2017.
 */
public class ClienteDaoImpl extends JpaDao<Cliente, Long> implements ClienteDao {

    public ClienteDaoImpl() {
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

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAll(Cliente cliente) {
        final CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<Cliente> cq = cb.createQuery(Cliente.class);

        Root<Cliente> root = cq.from(Cliente.class);
        if (!ObjectUtils.isEmpty(cliente.getNumeroDniRuc()))
            cq.where(cb.like(root.get("numeroDniRuc"), cliente.getNumeroDniRuc()));
        if (!ObjectUtils.isEmpty(cliente.getRazonSocialNombre()))
            cq.where(cb.like(root.get("razonSocialNombre"), cliente.getRazonSocialNombre()));
        if (!ObjectUtils.isEmpty(cliente.getTelefono()))
            cq.where(cb.like(root.get("telefono"), cliente.getTelefono()));
        if (!ObjectUtils.isEmpty(cliente.getDireccion()))
            cq.where(cb.like(root.get("direccion"), cliente.getDireccion()));
        if (!ObjectUtils.isEmpty(cliente.getEmail()))
            cq.where(cb.like(root.get("email"), cliente.getEmail()));

        cq.orderBy(cb.desc(root.get("razonSocialNombre")));

        TypedQuery<Cliente> typedQuery = this.getEntityManager().createQuery(cq);
        return typedQuery.getResultList();
    }


}
