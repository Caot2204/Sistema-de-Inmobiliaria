package mx.inmobiliaria.domain;

public class Cliente {

    private int idCliente;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nombreUsuario;
    private String contraseñaUsuario;
    private String email;
    private long numeroTelefonico;
    private String pais;
    private String ciudad;
    private int codigoPostal;
    private MetodoDePago metodoDePago;

    public Cliente(String nombre, String apellidoPaterno, String apellidoMaterno, String nombreUsuario, String contraseñaUsuario,
            String email, long numeroTelefonico, String pais, String ciudad, int codigoPostal, MetodoDePago metodoDePago) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoPaterno;
        this.nombreUsuario = nombreUsuario;
        this.contraseñaUsuario = contraseñaUsuario;
        this.email = email;
        this.numeroTelefonico = numeroTelefonico;
        this.pais = pais;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
        this.metodoDePago = metodoDePago;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Cliente() {

    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContraseñaUsuario() {
        return contraseñaUsuario;
    }

    public String getEmail() {
        return email;
    }

    public long getNumeroTelefonico() {
        return numeroTelefonico;
    }

    public String getPais() {
        return pais;
    }

    public String getIdCiudad() {
        return ciudad;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public MetodoDePago getMetodoDePago() {
        return metodoDePago;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public void setNombreNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setContraseñaUsuario(String contraseñaUsuario) {
        this.contraseñaUsuario = contraseñaUsuario;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumeroTelefonico(long numeroTelefonico) {
        this.numeroTelefonico = numeroTelefonico;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setMetodoDePago(MetodoDePago metodoDePago) {
        this.metodoDePago = metodoDePago;
    }
}
