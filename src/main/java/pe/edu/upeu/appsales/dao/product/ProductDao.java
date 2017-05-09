package pe.edu.upeu.appsales.dao.product;

import java.util.List;

import pe.edu.upeu.appsales.dao.Dao;
import pe.edu.upeu.appsales.model.Product;

public interface ProductDao extends Dao<Product,Long>{

    public List<Product> findAll();

}
