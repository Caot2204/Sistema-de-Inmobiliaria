
package mx.inmobiliaria.gui;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mx.inmobiliaria.dao.DepartamentoDAO;
import mx.inmobiliaria.domain.Cliente;
import mx.inmobiliaria.domain.Departamento;
import mx.inmobiliaria.domain.Hogar;
import mx.inmobiliaria.domain.TipoAdquisicion;

public class VRegistrarDepartamentoController implements Initializable {
    
    private Cliente cliente;
    private float precio;
    private String ubicacion;
    private int metrosCuadrados;
    private int habitaciones;
    private int baños;
    private String detallesExtras;
    private TipoAdquisicion tipoAdquisicion;
    private int pisoEnEdificio;
    
    private DepartamentoDAO departamentoDAO;

    @FXML
    private TextField textFieldUbicacion;
    
    @FXML
    private TextField textFieldPrecio;
    
    @FXML
    private TextField textFieldMetrosCuadrados;
    
    @FXML
    private TextArea textAreaDetalles;
    
    @FXML
    private ComboBox comboBoxHabitaciones;
    
    @FXML
    private ComboBox comboBoxBaños;
    
    @FXML
    private ComboBox comboBoxPisoEnEdificio;
    
    @FXML
    private ComboBox comboBoxTipoAdquisicion;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBoxHabitaciones.getItems().addAll("1","2","3","4","5","6","7","8","9","10");
        comboBoxBaños.getItems().addAll("1","2","3","4","5","6","7","8","9","10");
        comboBoxPisoEnEdificio.getItems().addAll("1","2","3","4","5","6","7","8","9","10");
        comboBoxTipoAdquisicion.getItems().addAll("venta","renta");
        
        comboBoxHabitaciones.setValue("1");
        comboBoxBaños.setValue("1");
        comboBoxPisoEnEdificio.setValue("1");
        comboBoxTipoAdquisicion.setValue("venta");
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public void guardarInmueble() {
        if (validarCampos()) {
            if (solicitarConfirmacionTerminarRegistro()) {
                obtenerDatosDeIU();
                Hogar detallesGenerales = new Hogar(cliente.getIdCliente(), precio, ubicacion, habitaciones, baños, metrosCuadrados, detallesExtras, tipoAdquisicion);
                Departamento departamento = new Departamento(detallesGenerales, pisoEnEdificio);
                departamentoDAO = new DepartamentoDAO();
                if (departamentoDAO.guardarDepartamento(departamento)){
                    mostrarMensajeExitoGuardado();
                    cerrarFormulario();
                }
                else {
                    mostrarMensajeFalloGuardado();
                }  
            } 
        }
    }
    
    public void cerrarFormulario() {
        Stage escenaActual = (Stage) textFieldUbicacion.getScene().getWindow();
        escenaActual.close();        
    }
    
    public void obtenerDatosDeIU() {
        precio = Float.parseFloat(textFieldPrecio.getText());
        ubicacion = textFieldUbicacion.getText();
        metrosCuadrados = Integer.parseInt(textFieldMetrosCuadrados.getText());
        habitaciones = Integer.parseInt(comboBoxHabitaciones.getValue().toString());
        baños = Integer.parseInt(comboBoxBaños.getValue().toString());
        detallesExtras = textAreaDetalles.getText();
        tipoAdquisicion = TipoAdquisicion.valueOf(comboBoxTipoAdquisicion.getValue().toString());
        pisoEnEdificio = Integer.parseInt(comboBoxPisoEnEdificio.getValue().toString());
    }
    
    public boolean validarCampos() {
        boolean todosValidos = true;
        if (textFieldUbicacion.getText().equals("")) {
            mostrarMensajeFaltanCamposObliagatorios();
            textFieldUbicacion.requestFocus();
            todosValidos = false;
        }
        else if (textFieldPrecio.getText().equals("")) {
            mostrarMensajeFaltanCamposObliagatorios();
            textFieldPrecio.requestFocus();
            todosValidos = false;
        }
        else if (textFieldMetrosCuadrados.getText().equals("")) {
            mostrarMensajeFaltanCamposObliagatorios();
            textFieldMetrosCuadrados.requestFocus();
            todosValidos = false;
        }
        
        return todosValidos;
    }
    
    public void mostrarMensajeFaltanCamposObliagatorios() {
        Alert mensajeFaltanCampos = new Alert(Alert.AlertType.ERROR);
        mensajeFaltanCampos.setTitle("Datos faltantes");
        mensajeFaltanCampos.setHeaderText(null);
        mensajeFaltanCampos.setContentText("Por favor ingrese los campos faltantes");

        mensajeFaltanCampos.showAndWait();
    }
    
    public boolean solicitarConfirmacionTerminarRegistro() {
        boolean confirmacion = false;
        
        Alert mensajeConfirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        mensajeConfirmacion.setTitle("Terminar registro");
        mensajeConfirmacion.setHeaderText("¿Desea terminar el registro del Inmueble?, se aplicará el cobro por publicidad a su método de pago registrado en el sistema");
        mensajeConfirmacion.setContentText(null);

        Optional<ButtonType> opcionElegida = mensajeConfirmacion.showAndWait();
        
        if (opcionElegida.get() == ButtonType.OK) {
            confirmacion = true;            
        }
        
        return confirmacion;
    }
    
    public void mostrarMensajeExitoGuardado() {
        Alert mensajeExito = new Alert(Alert.AlertType.INFORMATION);
        mensajeExito.setTitle("Éxito al guardar.");
        mensajeExito.setHeaderText(null);
        mensajeExito.setContentText("¡El Inmueble a sido publicado en el Catálogo con éxito!");

        mensajeExito.showAndWait();
    }
    
    public void mostrarMensajeFalloGuardado() {
        Alert mensajeFallo = new Alert(Alert.AlertType.ERROR);
        mensajeFallo.setTitle("Error al guardar.");
        mensajeFallo.setHeaderText(null);
        mensajeFallo.setContentText("Error al guardar iInmueble, intente de nuevo");

        mensajeFallo.showAndWait();        
    }
    
}
