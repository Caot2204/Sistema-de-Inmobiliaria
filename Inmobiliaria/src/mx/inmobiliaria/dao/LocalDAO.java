
package mx.inmobiliaria.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.inmobiliaria.database.DataBase;
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
        FileInputStream inputStreamA = null;
        FileInputStream inputStreamB = null;
        FileInputStream inputStreamC = null;
        FileInputStream inputStreamD = null;
        consultaSQL = "INSERT INTO local (Id_Cliente,Precio,Ubicacion,Metros_Cuadrados,Detalles_Extras,Tipo_Local,Tipo_Adquisicion,Imagen_A,Imagen_B,Imagen_C,Imagen_D) values (?,?,?,?,?,?,?,?,?,?,?)";
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
            
            if (local.getImagenes()[0] != null) {
                inputStreamA = new FileInputStream(local.getImagenes()[0]);
                sentenciaSQL.setBinaryStream(8, inputStreamA, local.getImagenes()[0].length());
            }
            else {
                sentenciaSQL.setBinaryStream(8, null);
            }
            
            if (local.getImagenes()[1] != null) {
                inputStreamB = new FileInputStream(local.getImagenes()[1]);
                sentenciaSQL.setBinaryStream(9, inputStreamB, local.getImagenes()[1].length());
            }
            else {
                sentenciaSQL.setBinaryStream(9, null);
            }
            
            if (local.getImagenes()[2] != null) {
                inputStreamC = new FileInputStream(local.getImagenes()[2]);
                sentenciaSQL.setBinaryStream(10, inputStreamC, local.getImagenes()[2].length());
            }
            else {
                sentenciaSQL.setBinaryStream(10, null);
            }
            
            if (local.getImagenes()[3] != null) {
                inputStreamD = new FileInputStream(local.getImagenes()[3]);
                sentenciaSQL.setBinaryStream(11, inputStreamD, local.getImagenes()[3].length());
            }
            else {
                sentenciaSQL.setBinaryStream(11, null);
            }
            sentenciaSQL.execute();
        } 
        catch (SQLException ex) {
            guardadoRealizado = false;
            Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LocalDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                File[] imagenes = null;
                local = new Local(idCliente, precio, ubicacion, metrosCuadrados, detallesExtras, tipoLocal, tipoAdquisicion, imagenes);                
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
