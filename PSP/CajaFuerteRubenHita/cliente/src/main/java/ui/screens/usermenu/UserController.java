package ui.screens.usermenu;

import jakarta.inject.Inject;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Carpeta;
import modelo.Message;
import retrofit.network.CacheAuthorization;
import ui.common.BasePantallaController;

public class UserController extends BasePantallaController {
    @FXML
    private TextField contrasenyaDesbloqueoField;
    @FXML
    private TableView<Message> mensajesTable;
    @FXML
    private TableColumn<String, Message> idMensajeColumn;
    @FXML
    private TableColumn<String, Message> textMensajeColum;
    @FXML
    private TextArea messageTextField;
    @FXML
    private Label actualUserLabel;
    @FXML
    private TextField permisoField;
    @FXML
    private TextField passField;
    @FXML
    private TextField carpetaNameField;
    @FXML
    private TableView<Carpeta> carpetaTable;
    @FXML
    private TableColumn<String, Carpeta> nameColumn;
    @FXML
    private TableColumn<String, Carpeta> permisoColumn;
    @FXML
    private TableColumn<String, Carpeta> usuarioColumn;

    private final UserViewModel userViewModel;

    CacheAuthorization ca;

    @Inject
    public UserController(UserViewModel userViewModel, CacheAuthorization ca) {
        this.userViewModel = userViewModel;
        this.ca = ca;
    }

    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        permisoColumn.setCellValueFactory(new PropertyValueFactory<>("permission"));
        usuarioColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        idMensajeColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        textMensajeColum.setCellValueFactory(new PropertyValueFactory<>("text"));

        userViewModel.getState().addListener((observableValue, listadoState, stateNew) -> Platform.runLater(() -> {
            Alert a;
            if (stateNew.error() != null) {
                a = new Alert(Alert.AlertType.ERROR);
                a.setContentText(stateNew.error());
                a.show();
            }
            if (stateNew.carpetaList() != null) {
                carpetaTable.getItems().clear();
                carpetaTable.getItems().addAll(stateNew.carpetaList());
            }
            if (stateNew.carpetaAnyadida() != null) {
                a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Carpeta " + stateNew.carpetaAnyadida().getName() + " añadida.");
                a.show();
            }
            if (stateNew.mensajeAnyadido() != null) {
                a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Mensaje añadido");
                a.show();
            }
            if (stateNew.messageList() != null) {
                mensajesTable.getItems().clear();
                mensajesTable.getItems().addAll(stateNew.messageList());
            }

            if (stateNew.status() != null){
                a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText(stateNew.status());
                a.show();
            }
            getPrincipalController().root.setCursor(Cursor.DEFAULT);
        }));
    }

    @Override
    public void principalCargado() {
        getAll();
        actualUserLabel.setText("Eres: " + ca.getUser());

        carpetaTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->{
            getPrincipalController().root.setCursor(Cursor.WAIT);
            if (newSelection == null){
                userViewModel.getMessages(oldSelection.getId());
            } else {
                userViewModel.getMessages(newSelection.getId());
            }
        });
    }

    @FXML
    private void crearCarpeta() {
        getPrincipalController().root.setCursor(Cursor.WAIT);

        String name = carpetaNameField.getText();
        int permiso = Integer.parseInt(permisoField.getText());
        String pass = passField.getText();

        userViewModel.postCarpeta(name, permiso, pass);
    }

    @FXML
    private void reload() {
        getAll();
    }

    private void getAll() {
        getPrincipalController().root.setCursor(Cursor.WAIT);
        userViewModel.getAll();
    }

    @FXML
    private void crearMensaje() {
        getPrincipalController().root.setCursor(Cursor.WAIT);
        int carpetaId = carpetaTable.getSelectionModel().getSelectedItem().getId();
        userViewModel.postMensaje(carpetaId, messageTextField.getText());
    }

    @FXML
    private void desbloquearCarpeta() {
        getPrincipalController().root.setCursor(Cursor.WAIT);
        int carpetaId = carpetaTable.getSelectionModel().getSelectedItem().getId();
        userViewModel.desbloquearCarpeta(carpetaId, contrasenyaDesbloqueoField.getText());
    }

    @FXML
    private void deleteMessage() {
        getPrincipalController().root.setCursor(Cursor.WAIT);
        userViewModel.borrarMensaje(mensajesTable.getSelectionModel().getSelectedItem());
    }
}
