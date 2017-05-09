package pe.edu.upeu.appsales.dao.product;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pe.edu.upeu.appsales.model.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:context.xml"})
public class ProductDaoImplTest {

	
    @Autowired
    @Qualifier("productDao")
    ProductDao productDao;

    @Test
    public void testFindAll() {
    	assertEquals("5", productDao.findAll().size()+"");
    }
    
    @Test
    public void testFind() {
    	Product product = productDao.find(7L);
    	assertEquals("TOYOTA", product.getNameProduct());
    	assertEquals("Toyota Yaris 2014", product.getDescription());
        assertEquals("15800.0", product.getPrice()+"");
        
    }

    @Test
    public void testSave() {
    	Product product = new Product();
    	product.setNameProduct("TOYOTA");
    	product.setDescription("Toyota COROLLA 2014");
    	product.setPrice(15800);
    	productDao.save(product);
    }

    @Test
    public void testDelete() {
    	List<Product> list = productDao.findAll();
    	int size=list.size();
    	
    	Product product=list.get(size-1);

    	productDao.delete(product.getId());
    	
    	product = productDao.find(product.getId());
    	assertNull(product);
    	
    }
    

}
