package pe.edu.upeu.appsales.rest.resources;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pe.edu.upeu.appsales.dao.cliente.ClienteDao;
import pe.edu.upeu.appsales.model.Cliente;
import pe.edu.upeu.appsales.JsonViews;
import pe.edu.upeu.appsales.model.Role;
import pe.edu.upeu.appsales.util.MatchModeSearch;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

import static pe.edu.upeu.appsales.model.Cliente.*;
import static pe.edu.upeu.appsales.util.MatchModeSearch.ANYWHERE;

/**
 * Created by Eduardo on 26/04/2017.
 */

@Component
@Path("/cliente")
public class ClienteResource {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ClienteDao clienteDao;

    @Autowired
    private ObjectMapper mapper;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String list() throws IOException {
        this.logger.info("list()");

        ObjectWriter viewWriter;
        if (this.isAdmin()) {
            viewWriter = this.mapper.writerWithView(JsonViews.Admin.class);
        } else {
            viewWriter = this.mapper.writerWithView(JsonViews.User.class);
        }
        List<Cliente> allEntries = this.clienteDao.findAll();

        return viewWriter.writeValueAsString(allEntries);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("findAll")
    public String findAll(Cliente cliente) throws IOException {
        this.logger.info("findAll(cliente)");

        ObjectWriter viewWriter;
        if (this.isAdmin()) {
            viewWriter = this.mapper.writerWithView(JsonViews.Admin.class);
        } else {
            viewWriter = this.mapper.writerWithView(JsonViews.User.class);
        }

        ClienteBuilder builder = new ClienteBuilder();
        if (!ObjectUtils.isEmpty(cliente.getNumeroDniRuc()))
            builder.numeroRucDni(ANYWHERE.toMatchString(cliente.getNumeroDniRuc()));
        if (!ObjectUtils.isEmpty(cliente.getRazonSocialNombre()))
            builder.razonSocialNombre(ANYWHERE.toMatchString(cliente.getRazonSocialNombre()));
        if (!ObjectUtils.isEmpty(cliente.getTelefono()))
            builder.telefono(ANYWHERE.toMatchString(cliente.getTelefono()));
        if (!ObjectUtils.isEmpty(cliente.getDireccion()))
            builder.direccion(ANYWHERE.toMatchString(cliente.getDireccion()));
        if (!ObjectUtils.isEmpty(cliente.getEmail()))
            builder.email(ANYWHERE.toMatchString(cliente.getEmail()));

        List<Cliente> allEntries = this.clienteDao.findAll(builder.build());

        return viewWriter.writeValueAsString(allEntries);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Cliente read(@PathParam("id") Long id) {
        this.logger.info("read(id)");

        Cliente cliente = this.clienteDao.find(id);
        if (cliente == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        return cliente;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Cliente create(Cliente cliente) {
        this.logger.info("create(): " + cliente);
        return this.clienteDao.save(cliente);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Cliente update(@PathParam("id") Long id, Cliente cliente) {
        this.logger.info("update(): " + cliente);

        return this.clienteDao.save(cliente);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public void delete(@PathParam("id") Long id) {
        this.logger.info("delete(id)");

        this.clienteDao.delete(id);
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
