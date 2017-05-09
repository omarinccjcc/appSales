package pe.edu.upeu.appsales.model;

import javax.persistence.*;

/**
 * Created by Eduardo on 25/04/2017.
 */
@Entity
public class Cliente implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 11, nullable = false)
    private String numeroDniRuc;

    @Column(length = 200, nullable = false)
    private String razonSocialNombre;

    @Column(length = 15)
    private String telefono;

    @Column(length = 100)
    private String email;

    @Column(length = 150)
    private String direccion;

    @Column(length = 10)
    private String estado;

    public Cliente() {
    }
    /*creado por ed*/

    public Cliente(String numeroDniRuc, String razonSocialNombre) {
        this.numeroDniRuc = numeroDniRuc;
        this.razonSocialNombre = razonSocialNombre;
    }

    public Cliente(ClienteBuilder builder) {
        this.numeroDniRuc = builder.numeroRucDni;
        this.razonSocialNombre = builder.razonSocialNombre;
        this.direccion = builder.direccion;
        this.email = builder.email;
        this.telefono = builder.telefono;
        this.estado = builder.estado;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroDniRuc() {
        return numeroDniRuc;
    }

    public void setNumeroDniRuc(String numeroDniRuc) {
        this.numeroDniRuc = numeroDniRuc;
    }

    public String getRazonSocialNombre() {
        return razonSocialNombre;
    }

    public void setRazonSocialNombre(String razonSocialNombre) {
        this.razonSocialNombre = razonSocialNombre;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", numeroDniRuc='" + numeroDniRuc + '\'' +
                ", razonSocialNombre='" + razonSocialNombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", direccion='" + direccion + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }

    public static class ClienteBuilder {

        private String numeroRucDni;

        private String razonSocialNombre;

        private String estado;

        private String direccion;

        private String telefono;

        private String email;

        public ClienteBuilder() {
        }

        public ClienteBuilder numeroRucDni(String numeroRucDni) {
            this.numeroRucDni = numeroRucDni;
            return this;
        }

        public ClienteBuilder razonSocialNombre(String razonSocialNombre) {
            this.razonSocialNombre = razonSocialNombre;
            return this;
        }

        public ClienteBuilder estado(String estado) {
            this.estado = estado;
            return this;
        }

        public ClienteBuilder direccion(String direccion) {
            this.direccion = direccion;
            return this;
        }

        public ClienteBuilder telefono(String telefono) {
            this.telefono = telefono;
            return this;
        }

        public ClienteBuilder email(String email) {
            this.email = email;
            return this;
        }

        public Cliente build() {
            return new Cliente(this);
        }
    }

}
