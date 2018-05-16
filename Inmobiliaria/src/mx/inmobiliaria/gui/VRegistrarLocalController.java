
package mx.inmobiliaria.gui;

import java.io.File;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import mx.inmobiliaria.dao.LocalDAO;
import mx.inmobiliaria.domain.Cliente;
import mx.inmobiliaria.domain.Local;
import mx.inmobiliaria.domain.TipoAdquisicion;
import mx.inmobiliaria.domain.TipoLocal;

public class VRegistrarLocalController implements Initializable {
    
    private Cliente cliente;
    private float precio;
    private String ubicacion;
    private int metrosCuadrados;
    private String detallesExtras;
    private TipoLocal tipoLocal;
    private TipoAdquisicion tipoAdquisicion;
    private File[] imagenes;
    
    private LocalDAO localDAO;

    @FXML
    private TextField textFieldUbicacion;
    
    @FXML
    private TextField textFieldPrecio;
    
    @FXML
    private TextField textFieldMetrosCuadrados;
    
    @FXML
    private TextArea textAreaDetalles;
    
    @FXML
    private ComboBox comboBoxTipoAdquisicion;
    
    @FXML
    private ComboBox comboBoxTipoLocal;
    
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
        comboBoxTipoAdquisicion.getItems().addAll("venta","renta");
        comboBoxTipoLocal.getItems().addAll("bodega","oficina");
        
        comboBoxTipoAdquisicion.setValue("venta");
        comboBoxTipoLocal.setValue("bodega");
        
        imagenes = new File[4];
    }  
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public void guardarInmueble() {
        if (validarCampos()) {
            if (solicitarConfirmacionTerminarRegistro()) {
                obtenerDatosDeIU();
                Local local = new Local(cliente.getIdCliente(), precio, ubicacion, metrosCuadrados, detallesExtras, tipoLocal, tipoAdquisicion, imagenes);
                localDAO = new LocalDAO();
                if (localDAO.guardarLocal(local)){
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
        Alert mensajeFaltanCampos = new Alert(Alert.AlertType.ERROR);
        mensajeFaltanCampos.setTitle("Tipo de archivo inválido");
        mensajeFaltanCampos.setHeaderText(null);
        mensajeFaltanCampos.setContentText("Por favor elija una imagen válida (.jpg o .png)");

        mensajeFaltanCampos.showAndWait();
    }
    
    public void obtenerDatosDeIU() {
        precio = Float.parseFloat(textFieldPrecio.getText());
        ubicacion = textFieldUbicacion.getText();
        metrosCuadrados = Integer.parseInt(textFieldMetrosCuadrados.getText());
        detallesExtras = textAreaDetalles.getText();
        tipoLocal = TipoLocal.valueOf(comboBoxTipoLocal.getValue().toString());
        tipoAdquisicion = TipoAdquisicion.valueOf(comboBoxTipoAdquisicion.getValue().toString());
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
