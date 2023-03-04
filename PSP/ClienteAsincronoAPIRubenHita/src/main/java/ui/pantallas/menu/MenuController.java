package ui.pantallas.menu;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ui.common.BasePantallaController;
import ui.common.Pantallas;

import java.util.Objects;

public class MenuController extends BasePantallaController {

    @FXML
    private ImageView titleImg;

    public void initialize(){
        try{
            titleImg.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/title.png"))));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void entrarPersonajes() {
        getPrincipalController().cargarPantalla(Pantallas.PERSONAJES);
    }

    public void entrarSitios() {
        getPrincipalController().cargarPantalla(Pantallas.SITIOS);
    }

    public void entrarEpisodios() {
        getPrincipalController().cargarPantalla(Pantallas.EPISODIOS);
    }
}
