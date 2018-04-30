
package mx.inmobiliaria.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

public class VRegistrarCasaController implements Initializable {
    
    @FXML
    private ComboBox comboBoxHabitaciones;
    
    @FXML
    private ComboBox comboBoxBaños;
    
    @FXML
    private ComboBox comboBoxPisos;
    
    @FXML
    private ComboBox comboBoxAutos;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBoxHabitaciones.getItems().addAll("1","2","3","4","5","6","7","8","9","10");
        comboBoxBaños.getItems().addAll("1","2","3","4","5","6","7","8","9","10");
        comboBoxPisos.getItems().addAll("1","2","3","4","5","6","7","8","9","10");
        comboBoxAutos.getItems().addAll("1","2","3","4","5","6","7","8","9","10");
    }    
    
}
