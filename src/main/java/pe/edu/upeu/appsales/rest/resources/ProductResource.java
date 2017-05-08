package pe.edu.upeu.appsales.rest.resources;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import pe.edu.upeu.appsales.JsonViews;
import pe.edu.upeu.appsales.dao.product.ProductDao;
import pe.edu.upeu.appsales.model.Product;
import pe.edu.upeu.appsales.model.Role;

@Component
@Path("/product")
public class ProductResource {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProductDao productDao;

	@Autowired
	private ObjectMapper mapper;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String list() throws IOException {
		this.logger.info("list product()");

		ObjectWriter viewWriter;
		if (this.isAdmin()) {
			viewWriter = this.mapper.writerWithView(JsonViews.User.class);
		} else {
			viewWriter = this.mapper.writerWithView(JsonViews.User.class);
		}
		List<Product> allEntries = this.productDao.findAll();

		for (Product product : allEntries) {
			System.out.println("Id "+product.getId());
		}
		
		return viewWriter.writeValueAsString(allEntries);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Product read(@PathParam("id") Long id) {
		this.logger.info("product::. read(id)");

		Product product = this.productDao.find(id);
		if (product == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}

		return product;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Product create(Product product) {
		this.logger.info("create(): " + product);
		return this.productDao.save(product);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Product update(@PathParam("id") Long id, Product product) {
		this.logger.info("product::: update(): " + product);

		return this.productDao.save(product);
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public void delete(@PathParam("id") Long id) {
		this.logger.info("delete(id)");

		this.productDao.delete(id);
	}

	private boolean isAdmin() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();

		if (!(principal instanceof UserDetails)) {
			return false;
		}

		UserDetails userDetails = (UserDetails) principal;

		return userDetails.getAuthorities().contains(Role.ADMIN);
	}

}
