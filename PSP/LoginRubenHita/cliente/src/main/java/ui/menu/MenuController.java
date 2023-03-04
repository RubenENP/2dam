package ui.menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import ui.common.BasePantallaController;
import ui.common.Pantallas;

public class MenuController extends BasePantallaController {


    @FXML
    private void enterNewspapers() {
        getPrincipalController().cargarPantalla(Pantallas.NEWSPAPERS);
    }

    @FXML
    private void enterArticles() {
        getPrincipalController().cargarPantalla(Pantallas.ARTICLES);
    }

    @FXML
    private void enterReaders() {
        getPrincipalController().cargarPantalla(Pantallas.READERS);
    }

    @FXML
    private void enterUser() {getPrincipalController().cargarPantalla(Pantallas.LOGIN);}
}
