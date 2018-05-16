
package mx.inmobiliaria.gui;

import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import mx.inmobiliaria.dao.CasaDAO;
import mx.inmobiliaria.domain.Casa;
import mx.inmobiliaria.domain.Cliente;
import mx.inmobiliaria.domain.Hogar;
import mx.inmobiliaria.domain.TipoAdquisicion;

public class VRegistrarCasaController implements Initializable {
    
    private Cliente cliente;
    
    private float precio;
    private String ubicacion;
    private int metrosCuadrados;
    private int habitaciones;
    private int baños;
    private String detallesExtras;
    private TipoAdquisicion tipoAdquisicion;
    private File[] imagenes;
    private int pisosDeCasa;
    private boolean tieneGaraje;
    private int numeroAutos;
    private boolean tienePatio;
    private int metrosPatio;
    
    private CasaDAO casaDAO;
    
    @FXML
    private TextField textFieldUbicacion;
    
    @FXML
    private TextField textFieldPrecio;
    
    @FXML
    private TextField textFieldMetrosCuadrados;
    
    @FXML
    private CheckBox checkBoxGaraje;
    
    @FXML
    private CheckBox checkBoxPatio;
    
    @FXML
    private TextField textFieldMCPatio;

    @FXML
    private TextArea textAreaDetalles;
    
    @FXML
    private ComboBox comboBoxHabitaciones;
    
    @FXML
    private ComboBox comboBoxBaños;
    
    @FXML
    private ComboBox comboBoxPisos;
    
    @FXML
    private ComboBox comboBoxAutos;
    
    @FXML
    private ComboBox comboBoxTipoAdquisicion;
    
    @FXML
    private ImageView imageViewA;
    
    @FXML
    private ImageView imageViewB;
    
    @FXML
    private ImageView imageViewC;
    
    @FXML
    private ImageView imageViewD;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBoxHabitaciones.getItems().addAll("1","2","3","4","5","6","7","8","9","10");
        comboBoxBaños.getItems().addAll("1","2","3","4","5","6","7","8","9","10");
        comboBoxPisos.getItems().addAll("1","2","3","4","5","6","7","8","9","10");
        comboBoxAutos.getItems().addAll("0","1","2","3","4","5","6","7","8","9","10");
        comboBoxTipoAdquisicion.getItems().addAll("venta","renta");
        
        comboBoxHabitaciones.setValue("1");
        comboBoxBaños.setValue("1");
        comboBoxPisos.setValue("1");
        comboBoxAutos.setValue("1");
        comboBoxTipoAdquisicion.setValue("venta");
        
        textFieldMCPatio.setDisable(true);
        comboBoxAutos.setDisable(true);
        
        imagenes = new File[4];
        imagenes[0] = null;
        imagenes[1] = null;
        imagenes[2] = null;
        imagenes[3] = null;
    }
    
    public void cambiarEstadoComboAutos() {
        if (checkBoxGaraje.selectedProperty().get()) {
            comboBoxAutos.setDisable(false);
        }
        else {
            comboBoxAutos.setDisable(true);
            comboBoxAutos.setValue("0");
        }
    }
    
    public void cambiarEstadoMCPatio() {
        if (checkBoxPatio.selectedProperty().get()) {
            textFieldMCPatio.setDisable(false);            
        }
        else {
            textFieldMCPatio.setDisable(true);
        }
        
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public void guardarInmueble() {
        if (validarCampos()) {
            if (solicitarConfirmacionTerminarRegistro()) {
                obtenerDatosDeIU();
                Hogar detallesGenerales = new Hogar(cliente.getIdCliente(), precio, ubicacion, habitaciones, baños, metrosCuadrados, detallesExtras, tipoAdquisicion, imagenes);
                Casa casa = new Casa(detallesGenerales, pisosDeCasa, tienePatio, metrosPatio, tieneGaraje, numeroAutos);
                casaDAO = new CasaDAO();
                if (casaDAO.guardarDatosCasa(casa)){
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
    
    public File cargarImagen() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Escoja una imagen");
        return fileChooser.showOpenDialog(imageViewA.getScene().getWindow());
    }
    
    public void seleccionarImagenA() {
        File archivoElegido = cargarImagen();
        if (archivoElegido != null){
            if (archivoElegido.getName().endsWith(".jpg") || archivoElegido.getName().endsWith(".png")){
                imagenes[0] = archivoElegido;
                Image imagen = new Image("file:" + imagenes[0].getAbsolutePath());
                imageViewA.setImage(imagen);                
            }
            else {
                mostrarMensajeArchivoInvalido();
            }
        }
    }
    
    public void seleccionarImagenB() {
        File archivoElegido = cargarImagen();
        if (archivoElegido != null){
            if (archivoElegido.getName().endsWith(".jpg") || archivoElegido.getName().endsWith(".png")){
                imagenes[1] = archivoElegido;
                Image imagen = new Image("file:" + imagenes[1].getAbsolutePath());
                imageViewB.setImage(imagen);                
            }
            else {
                mostrarMensajeArchivoInvalido();
            }
        }
    }
    
    public void seleccionarImagenC() {
        File archivoElegido = cargarImagen();
        if (archivoElegido != null){
            if (archivoElegido.getName().endsWith(".jpg") || archivoElegido.getName().endsWith(".png")){
                imagenes[2] = archivoElegido;
                Image imagen = new Image("file:" + imagenes[2].getAbsolutePath());
                imageViewC.setImage(imagen);                
            }
            else {
                mostrarMensajeArchivoInvalido();
            }
        }
    }
    
    public void seleccionarImagenD() {
        File archivoElegido = cargarImagen();
        if (archivoElegido != null){
            if (archivoElegido.getName().endsWith(".jpg") || archivoElegido.getName().endsWith(".png")){
                imagenes[3] = archivoElegido;
                Image imagen = new Image("file:" + imagenes[3].getAbsolutePath());
                imageViewD.setImage(imagen);                 
            }
            else {
                mostrarMensajeArchivoInvalido();
            }
        }
    }
    
    public void mostrarMensajeArchivoInvalido() {
        Alert mensajeFaltanCampos = new Alert(AlertType.ERROR);
        mensajeFaltanCampos.setTitle("Tipo de archivo inválido");
        mensajeFaltanCampos.setHeaderText(null);
        mensajeFaltanCampos.setContentText("Por favor elija una imagen válida (.jpg o .png)");

        mensajeFaltanCampos.showAndWait();
    }
    
    public void obtenerDatosDeIU() {
        precio = Float.parseFloat(textFieldPrecio.getText());
        ubicacion = textFieldUbicacion.getText();
        metrosCuadrados = Integer.parseInt(textFieldMetrosCuadrados.getText());
        habitaciones = Integer.parseInt(comboBoxHabitaciones.getValue().toString());
        baños = Integer.parseInt(comboBoxBaños.getValue().toString());
        detallesExtras = textAreaDetalles.getText();
        tipoAdquisicion = TipoAdquisicion.valueOf(comboBoxTipoAdquisicion.getValue().toString());
        pisosDeCasa = Integer.parseInt(comboBoxPisos.getValue().toString());
        
        tieneGaraje = checkBoxGaraje.selectedProperty().get();
        if (tieneGaraje){
            numeroAutos = Integer.parseInt(comboBoxAutos.getValue().toString());
        }
        else {
            numeroAutos = 0;
        }
        
        tienePatio = checkBoxPatio.selectedProperty().get();
        if (tienePatio) {
            metrosPatio = Integer.parseInt(textFieldMCPatio.getText());
        }
        else {
            metrosPatio = 0;
        }
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
        Alert mensajeFaltanCampos = new Alert(AlertType.ERROR);
        mensajeFaltanCampos.setTitle("Datos faltantes");
        mensajeFaltanCampos.setHeaderText(null);
        mensajeFaltanCampos.setContentText("Por favor ingrese los campos faltantes");

        mensajeFaltanCampos.showAndWait();
    }
    
    public boolean solicitarConfirmacionTerminarRegistro() {
        boolean confirmacion = false;
        
        Alert mensajeConfirmacion = new Alert(AlertType.CONFIRMATION);
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
        Alert mensajeExito = new Alert(AlertType.INFORMATION);
        mensajeExito.setTitle("Éxito al guardar.");
        mensajeExito.setHeaderText(null);
        mensajeExito.setContentText("¡El Inmueble a sido publicado en el Catálogo con éxito!");

        mensajeExito.showAndWait();
    }
    
    public void mostrarMensajeFalloGuardado() {
        Alert mensajeFallo = new Alert(AlertType.ERROR);
        mensajeFallo.setTitle("Error al guardar.");
        mensajeFallo.setHeaderText(null);
        mensajeFallo.setContentText("Error al guardar el Inmueble, intente de nuevo");

        mensajeFallo.showAndWait();        
    }

}
