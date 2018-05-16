package mx.inmobiliaria.gui;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mx.inmobiliaria.dao.ClienteDAO;
import mx.inmobiliaria.domain.Cliente;
import mx.inmobiliaria.domain.MetodoDePago;

public class VClienteController implements Initializable {

    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nombreUsuario;
    private String contraseñaUsuario;
    private String email;
    private long numeroTelefonico;
    private String pais;
    private String ciudad;
    private int codigoPostal;
    private MetodoDePago metodoDePago;

    private Cliente clienteDAO;

    @FXML
    private TextField textFieldNombre;
    @FXML
    private TextField textFieldApellidoPaterno;
    @FXML
    private TextField textFieldApellidoMaterno;
    @FXML
    private TextField textFieldNombreUsuario;
    @FXML
    private TextField textFieldContraseñaUsuario;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private TextField textFieldNumeroTelefonico;
    @FXML
    private TextField textFieldPais;
    @FXML
    private TextField textFieldCiudad;
    @FXML
    private TextField textFieldCodigoPostal;
    @FXML
    private TextField textFieldNumeroDeCuenta;
    @FXML
    private ComboBox comboBoxMetodoDePago;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBoxMetodoDePago.getItems().addAll("Debito", "Credito");
        comboBoxMetodoDePago.setValue("Debito");
    }

    public void mostrarMensajeFaltanCampos() {
        Alert mensajeFaltanCampos = new Alert(Alert.AlertType.ERROR);
        mensajeFaltanCampos.setTitle("Datos faltantes");
        mensajeFaltanCampos.setHeaderText(null);
        mensajeFaltanCampos.setContentText("Por favor ingrese los campos faltantes");

        mensajeFaltanCampos.showAndWait();
    }

    public boolean validarCampos() {
        boolean todosValidos = true;
        if (textFieldNombre.getText().equals("")) {
            mostrarMensajeFaltanCampos();
            textFieldNombre.requestFocus();
            todosValidos = false;
        } else if (textFieldApellidoPaterno.getText().equals("")) {
            mostrarMensajeFaltanCampos();
            textFieldApellidoPaterno.requestFocus();
            todosValidos = false;
        } else if (textFieldApellidoMaterno.getText().equals("")) {
            mostrarMensajeFaltanCampos();
            textFieldApellidoMaterno.requestFocus();
            todosValidos = false;
        } else if (textFieldNombreUsuario.getText().equals("")) {
            mostrarMensajeFaltanCampos();
            textFieldNombreUsuario.requestFocus();
            todosValidos = false;
        } else if (textFieldContraseñaUsuario.getText().equals("")) {
            mostrarMensajeFaltanCampos();
            textFieldContraseñaUsuario.requestFocus();
            todosValidos = false;
        } else if (textFieldEmail.getText().equals("")) {
            mostrarMensajeFaltanCampos();
            textFieldEmail.requestFocus();
            todosValidos = false;
        } else if (textFieldNumeroTelefonico.getText().equals("")) {
            mostrarMensajeFaltanCampos();
            textFieldNumeroTelefonico.requestFocus();
            todosValidos = false;
        } else if (textFieldPais.getText().equals("")) {
            mostrarMensajeFaltanCampos();
            textFieldPais.requestFocus();
            todosValidos = false;
        } else if (textFieldCiudad.getText().equals("")) {
            mostrarMensajeFaltanCampos();
            textFieldCiudad.requestFocus();
            todosValidos = false;
        } else if (textFieldCodigoPostal.getText().equals("")) {
            mostrarMensajeFaltanCampos();
            textFieldCodigoPostal.requestFocus();
            todosValidos = false;
        }

        return todosValidos;
    }

    @FXML
    public void guardarCliente() {
        if (validarCampos()) {
            if (solicitarConfirmacionTerminar()) {
                obtenerDatosDeIU();
                Cliente cliente = new Cliente(nombre, apellidoPaterno, apellidoMaterno, nombreUsuario, contraseñaUsuario, email, numeroTelefonico, pais, ciudad, codigoPostal, metodoDePago);
                ClienteDAO clienteDAO = new ClienteDAO();
                if (clienteDAO.guardarDatosCliente(cliente))     {
                    mostrarMensajeExitoGuardado();
                    cerrarFormulario();
                } else {
                    mostrarMensajeFalloGuardado();
                }
            }
        }
    }

    @FXML
    public void cerrarFormulario() {
        Stage escenaActual = (Stage) textFieldNombre.getScene().getWindow();
        escenaActual.close();
    }

    public void obtenerDatosDeIU() {
        metodoDePago = new MetodoDePago();
         nombreUsuario = textFieldNombreUsuario.getText();
        contraseñaUsuario = textFieldContraseñaUsuario.getText();
        nombre = textFieldNombre.getText();
        apellidoPaterno = textFieldApellidoPaterno.getText();
        apellidoMaterno = textFieldApellidoMaterno.getText();
        numeroTelefonico = Long.parseLong(textFieldNumeroTelefonico.getText());
        email = textFieldEmail.getText();
        codigoPostal = Integer.parseInt(textFieldCodigoPostal.getText());
        pais = textFieldPais.getText();
        ciudad = textFieldCiudad.getText();
        metodoDePago.setTipoTarjeta(comboBoxMetodoDePago.getValue().toString()); 
        metodoDePago.setTipoNumero(Long.parseLong(textFieldNumeroDeCuenta.getText()));
      
    }

    public boolean solicitarConfirmacionTerminar() {// Manda mensaje para confirmar que desea guardar un nuevo cliente
        boolean confirmacion = false;

        Alert mensajeConfirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        mensajeConfirmacion.setTitle("Terminar registro");
        mensajeConfirmacion.setHeaderText("¿Desea terminar de crear su usuario?");
        mensajeConfirmacion.setContentText(null);

        Optional<ButtonType> opcionElegida = mensajeConfirmacion.showAndWait();

        if (opcionElegida.get() == ButtonType.OK) {
            confirmacion = true;
        }

        return confirmacion;
    }

    public void mostrarMensajeExitoGuardado() {
        Alert mensajeExito = new Alert(Alert.AlertType.INFORMATION);//Mensaje informando que el usuario a sido guardado con extio
        mensajeExito.setTitle("Éxito al guardar nuevo usuario.");
        mensajeExito.setHeaderText(null);
        mensajeExito.setContentText("¡El usuario fue creado con exito!");

        mensajeExito.showAndWait();
    }

    public void mostrarMensajeFalloGuardado() {
        Alert mensajeFallo = new Alert(Alert.AlertType.ERROR);// Manda error indicando que no se ha guardado correctamemte su información
        mensajeFallo.setTitle("Error al guardar.");
        mensajeFallo.setHeaderText(null);
        mensajeFallo.setContentText("Error al guardar el usuario, intente de nuevo.");

        mensajeFallo.showAndWait();
    }
}
