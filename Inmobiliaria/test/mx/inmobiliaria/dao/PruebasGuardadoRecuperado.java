
package mx.inmobiliaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.inmobiliaria.database.DataBase;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({SuitePruebasAlmacenarInmuebles.class, SuitePruebasRecuperarInmuebles.class})

public class PruebasGuardadoRecuperado {
    
    @AfterClass
    public static void limpiarDatosUtilizadosEnPruebas(){
        Connection conexionDB = DataBase.getDataBaseConnection();
        ArrayList<String> consultasSQL = new ArrayList<>();
        consultasSQL.add("DELETE FROM casa where Id_Hogar=? and Id_Cliente=?");        
        consultasSQL.add("DELETE FROM departamento where Id_Hogar=? and Id_Cliente=?");
        
        for (int a = 0; a < consultasSQL.size(); a++){
            try {
                PreparedStatement sentenciaSQL = conexionDB.prepareStatement(consultasSQL.get(a));
                sentenciaSQL.setInt(1, 1);
                sentenciaSQL.setInt(2, 1);
                sentenciaSQL.execute();
            } catch (SQLException ex) {
                Logger.getLogger(PruebasGuardadoRecuperado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
}
