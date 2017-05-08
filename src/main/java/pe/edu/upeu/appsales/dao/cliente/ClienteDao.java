package pe.edu.upeu.appsales.dao.cliente;

import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.appsales.dao.Dao;
import pe.edu.upeu.appsales.model.Cliente;

import java.util.List;

/**
 * Created by Eduardo on 25/04/2017.
 */
public interface ClienteDao extends Dao<Cliente,Long> {

    @Transactional(readOnly = true)
    List<Cliente> findAll(Cliente cliente);
}
