
package mx.inmobiliaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.inmobiliaria.database.DataBase;
import mx.inmobiliaria.domain.Departamento;
import mx.inmobiliaria.domain.Hogar;
import mx.inmobiliaria.domain.Local;
import mx.inmobiliaria.domain.TipoAdquisicion;
import mx.inmobiliaria.domain.TipoLocal;

public class LocalDAO implements ILocalDAO {
    
    private String consultaSQL;
    private Connection conexionDB;
    private PreparedStatement sentenciaSQL;

    @Override
    public boolean guardarLocal(Local local) {
        boolean guardadoRealizado = true;
        consultaSQL = "INSERT INTO local (Id_Cliente,Precio,Ubicacion,Metros_Cuadrados,Detalles_Extras,Tipo_Local,Tipo_Adquisicion) values (?,?,?,?,?,?,?)";
        conexionDB = DataBase.getDataBaseConnection();
        try{
            sentenciaSQL = conexionDB.prepareStatement(consultaSQL);
            sentenciaSQL.setInt(1, local.getIdDueño());
            sentenciaSQL.setFloat(2, local.getPrecio());
            sentenciaSQL.setString(3, local.getUbicacion());
            sentenciaSQL.setInt(4, local.getMetrosCuadrados());
            sentenciaSQL.setString(5, local.getDetallesExtras());
            sentenciaSQL.setString(6, local.getTipoDeLocal().name());
            sentenciaSQL.setString(7, local.getTipoAdquisicion().name());
            sentenciaSQL.execute();
        } 
        catch (SQLException ex) {
            guardadoRealizado = false;
            Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            DataBase.closeConnection();
        }
        return guardadoRealizado;
    }

    @Override
    public Local obtenerDatosLocal(int idHogar, int idCliente) {
        Local local = null;
        consultaSQL = "SELECT * FROM local WHERE Id_Local=? and Id_Cliente=?";
        conexionDB = DataBase.getDataBaseConnection();
        try{
            sentenciaSQL = conexionDB.prepareStatement(consultaSQL);
            sentenciaSQL.setInt(1, 1);
            sentenciaSQL.setInt(2, 1);
            ResultSet resultadoConsulta = sentenciaSQL.executeQuery();
            while (resultadoConsulta.next()){
                float precio = resultadoConsulta.getFloat("Precio");
                String ubicacion = resultadoConsulta.getString("Ubicacion");
                int metrosCuadrados = resultadoConsulta.getInt("Metros_Cuadrados");
                String detallesExtras = resultadoConsulta.getString("Detalles_Extras");
                TipoLocal tipoLocal = TipoLocal.valueOf(resultadoConsulta.getString("Tipo_Local"));
                TipoAdquisicion tipoAdquisicion = TipoAdquisicion.valueOf(resultadoConsulta.getString("Tipo_Adquisicion"));
                
                local = new Local(idCliente, precio, ubicacion, metrosCuadrados, detallesExtras, tipoLocal, tipoAdquisicion);                
            }
        } 
        catch (SQLException ex) {
            local = null;
            Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            DataBase.closeConnection();
        }
        return local;
    }
    
}
