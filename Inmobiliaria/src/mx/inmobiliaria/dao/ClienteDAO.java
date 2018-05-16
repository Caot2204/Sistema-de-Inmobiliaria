package mx.inmobiliaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.inmobiliaria.domain.Cliente;
import mx.inmobiliaria.database.DataBase;

public class ClienteDAO {

    private Connection conexionDB;
    private String consultaSQL;
    private PreparedStatement sentenciaSQL;

    public boolean guardarDatosCliente(Cliente cliente) {
        boolean guardadoRealizado = true;
        consultaSQL = "INSERT INTO cliente (Nombre_Usuario ,Contraseña_Usuario, Nombre, Apellido_Paterno ,Apellido_Materno, Num_Telefonico , Email, Codigo_Postal,  Pais, Ciudad, Tipo_Tarjeta, Numero_Tarjeta) values (?,?,?,?,?,?,?,?,?,?,?,?)";
        conexionDB = DataBase.getDataBaseConnection();
        try {
            sentenciaSQL = conexionDB.prepareStatement(consultaSQL);
            sentenciaSQL.setString(1, cliente.getNombreUsuario());
            sentenciaSQL.setString(2, cliente.getContraseñaUsuario());
            sentenciaSQL.setString(3, cliente.getNombre());
            sentenciaSQL.setString(4, cliente.getApellidoPaterno());
            sentenciaSQL.setString(5, cliente.getApellidoMaterno());
            sentenciaSQL.setLong(6, cliente.getNumeroTelefonico());
            sentenciaSQL.setString(7, cliente.getEmail());
            sentenciaSQL.setInt(8, cliente.getCodigoPostal());
            sentenciaSQL.setString(9, cliente.getPais());
            sentenciaSQL.setString(10, cliente.getCiudad());
            sentenciaSQL.setString(11, cliente.getMetodoDePago().getTipoTarjeta());
            sentenciaSQL.setLong(12, cliente.getMetodoDePago().getNumeroTarjeta());
            sentenciaSQL.execute();
        } catch (SQLException ex) {
            guardadoRealizado = false;
            Logger.getLogger(CasaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DataBase.closeConnection();
        }
        return guardadoRealizado;
    }

}
