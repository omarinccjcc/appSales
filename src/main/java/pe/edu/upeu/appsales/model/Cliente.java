package pe.edu.upeu.appsales.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Eduardo on 25/04/2017.
 */
@Entity
public class Cliente implements EntityGeneric {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, length = 11, nullable = false)
    private String numeroRuc;

    @Column(unique = true, length = 8, nullable = false)
    private String numeroDNI;

    @Column(length = 100, nullable = false)
    private  String razonSocial;

    @Column(length =15)
    private String tpoContribuyente;

    @Column(length = 150)
    private String nombreComercial;

    @Column(length = 10)
    private String estado;

    @Column(length = 150)
    private  String direccion;

    @Column(length=15)
    private String telefono;

    @Column(length = 15)
    private String fax;

    @Column(length = 100)
    private  String email;


    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    @Override
    public String toString()
    {
        return String.format("cliente[%d, %s]", this.id, this.getNumeroRuc(), this.getNumeroDNI(), this.getRazonSocial(), this.getNombreComercial(), this.getTelefono(), this.getEmail(), this.getDireccion(), this.getFax(), this.getTpoContribuyente(), this.getEstado());
    }

    public String getNumeroRuc() {
        return numeroRuc;
    }

    public void setNumeroRuc(String numeroRuc) {
        this.numeroRuc = numeroRuc;
    }

    public String getNumeroDNI() {
        return numeroDNI;
    }

    public void setNumeroDNI(String numeroDNI) {
        this.numeroDNI = numeroDNI;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getTpoContribuyente() {
        return tpoContribuyente;
    }

    public void setTpoContribuyente(String tpoContribuyente) {
        this.tpoContribuyente = tpoContribuyente;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
