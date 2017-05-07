package pe.edu.upeu.appsales.dao.product;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.transaction.annotation.Transactional;

import pe.edu.upeu.appsales.dao.JpaDao;
import pe.edu.upeu.appsales.model.Product;


public class ProductDaoImp  extends JpaDao<Product, Long> implements ProductDao{

    public  ProductDaoImp(){
        super(Product.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        final CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<Product> criteriaQuery = builder.createQuery(Product.class);

        Root<Product> root = criteriaQuery.from(Product.class);

        TypedQuery<Product> typedQuery = this.getEntityManager().createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }
    
    
    
}
