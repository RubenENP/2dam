package ui.screen.readArticle;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import services.ServicesReadArticle;
import ui.common.BaseScreenController;

public class AddReadArticleController extends BaseScreenController {
    public final ServicesReadArticle servicesReadArticle;

    @Inject
    public AddReadArticleController(ServicesReadArticle servicesReadArticle) {
        this.servicesReadArticle = servicesReadArticle;
    }

    @FXML
    void addReadArticle() {

    }
}
