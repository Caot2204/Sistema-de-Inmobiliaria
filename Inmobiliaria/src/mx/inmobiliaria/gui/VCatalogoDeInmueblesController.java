
package mx.inmobiliaria.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceDialog;
import javafx.stage.Stage;

public class VCatalogoDeInmueblesController implements Initializable {
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    public void mostrarCuadroDialogoOpcionesInmuebles(){
        List<String> opcionesInmuebles = new ArrayList<>();
        opcionesInmuebles.add("Casa");
        opcionesInmuebles.add("Departamento");
        opcionesInmuebles.add("Bodega/Oficina");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("", opcionesInmuebles);
        dialog.setTitle("Inmueble a registrar");
        dialog.setHeaderText("Tipo de Inmueble a registrar");
        dialog.setContentText("Elija un Inmueble de la lista:");
        
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            switch (result.get()){
                case "Casa": mostrarFormularioDeInmueble("Casa"); break;
                case "Departamento": mostrarFormularioDeInmueble("Departamento"); break;
                case "Bodega/Oficina": mostrarFormularioDeInmueble("Local"); break;
            }            
        }
    }
    
    public void mostrarFormularioDeInmueble(String tipoInmueble){
        String titulo = null;
        String formulario = null;
        
        switch (tipoInmueble){
            case "Casa":
                        formulario = "VRegistrarCasa.fxml";
                        titulo = "Registrar una Casa";
                        break;
            case "Departamento":
                        formulario = "VRegistrarDepartamento.fxml";
                        titulo = "Registrar un Departamento";
                        break;
            case "Local":
                        formulario = "VRegistrarLocal.fxml";
                        titulo = "Registrar una Bodega/Oficina";
                        break;   
        }
        
        try {
            Parent padre = FXMLLoader.load(getClass().getResource(formulario));
            Scene escena = new Scene(padre);
            Stage escenario = new Stage();
            escenario.setScene(escena);
            escenario.setTitle(titulo);
            escenario.show();
        } catch (IOException ex) {
            Logger.getLogger(VCatalogoDeInmueblesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
