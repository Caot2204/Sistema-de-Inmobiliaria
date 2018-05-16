
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
import mx.inmobiliaria.domain.Cliente;

public class VCatalogoDeInmueblesController implements Initializable {
    
    private Cliente cliente;
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cliente = new Cliente();
        cliente.setIdCliente(2);
           
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
        
        FXMLLoader cargadorIU = new FXMLLoader();        
        switch (tipoInmueble){
            case "Casa":
                        titulo = "Registrar una Casa";
                        cargadorIU.setLocation(getClass().getResource("VRegistrarCasa.fxml"));
                        try{
                            cargadorIU.load();
                        } catch (IOException ex) {
                            Logger.getLogger(VCatalogoDeInmueblesController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        VRegistrarCasaController casaController = cargadorIU.getController();
                        casaController.setCliente(cliente);
                        break;
            case "Departamento":
                        titulo = "Registrar un Departamento";
                        cargadorIU.setLocation(getClass().getResource("VRegistrarDepartamento.fxml"));
                        try{
                            cargadorIU.load();
                        } catch (IOException ex) {
                            Logger.getLogger(VCatalogoDeInmueblesController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        VRegistrarDepartamentoController departamentoController = cargadorIU.getController();
                        departamentoController.setCliente(cliente);
                        break;
            case "Local":
                        titulo = "Registrar una Bodega/Oficina";
                        cargadorIU.setLocation(getClass().getResource("VRegistrarLocal.fxml"));
                        try{
                            cargadorIU.load();
                        } catch (IOException ex) {
                            Logger.getLogger(VCatalogoDeInmueblesController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        VRegistrarLocalController localController = cargadorIU.getController();
                        localController.setCliente(cliente);
                        break;   
        }
        Parent padre = cargadorIU.getRoot();
        Scene escena = new Scene(padre);
        Stage escenario = new Stage();
        escenario.setScene(escena);
        escenario.setTitle(titulo);
        escenario.show();
    }
    
}
