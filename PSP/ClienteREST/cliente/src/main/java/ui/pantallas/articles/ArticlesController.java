package ui.pantallas.articles;

import domain.model.Article;
import domain.model.ArticleType;
import domain.model.Newspaper;
import jakarta.inject.Inject;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.common.BasePantallaController;
import ui.common.Pantallas;

public class ArticlesController extends BasePantallaController {
    public ComboBox<String> comboTipo1;
    public ComboBox<String> comboNewspaper;
    @FXML
    private ComboBox<String> comboTipo;
    @FXML
    private TableView<Article> articlesTable;
    @FXML
    private TableColumn<String, Article> idColumn;
    @FXML
    private TableColumn<String, Article> nameColumn;
    @FXML
    private TableColumn<String, Article> descriptionColumn;
    @FXML
    private TextField nameField;
    @FXML
    private TextArea descriptionField;

    private final ArticlesViewModel articlesViewModel;

    @Inject
    ArticlesController(ArticlesViewModel articlesViewModel) {
        this.articlesViewModel = articlesViewModel;
    }

    public void initialize(){
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name_article"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        cambiosEstado();
    }

    private void cambiosEstado() {
        articlesViewModel.getState().addListener((observableValue, listadoState, stateNew) -> Platform.runLater(() -> {
            if (stateNew.error() != null){
                getPrincipalController().sacarAlertError(stateNew.error());
            }

            if (stateNew.articleList() != null){
                articlesTable.getItems().clear();
                articlesTable.getItems().addAll(stateNew.articleList());
            }

            if (stateNew.articleTypeList() != null){
                comboTipo.getItems().clear();
                comboTipo.getItems().addAll(stateNew.articleTypeList().stream().map(ArticleType::getName).toList());

                comboTipo1.getItems().clear();
                comboTipo1.getItems().addAll(stateNew.articleTypeList().stream().map(ArticleType::getName).toList());
            }

            if (stateNew.newspaperList() != null){
                comboNewspaper.getItems().clear();
                comboNewspaper.getItems().addAll(stateNew.newspaperList().stream().map(Newspaper::getName_newspaper).toList());
            }

            getPrincipalController().root.setCursor(Cursor.DEFAULT);
        }));
    }

    @Override
    public void principalCargado() {
        reload();
    }

    @FXML
    private void atras() {
        getPrincipalController().cargarPantalla(Pantallas.MENU);
    }

    @FXML
    private void buscarPorTipo() {
        getPrincipalController().root.setCursor(Cursor.WAIT);
        articlesViewModel.getArticleById(comboTipo.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void addArticle() {
        getPrincipalController().root.setCursor(Cursor.WAIT);
        articlesViewModel.addArticle(nameField.getText(), descriptionField.getText(),
                comboTipo1.getSelectionModel().getSelectedItem(), comboNewspaper.getSelectionModel().getSelectedItem());
        reload();
    }

    @FXML
    private void reload() {
        getPrincipalController().root.setCursor(Cursor.WAIT);
        articlesViewModel.getAllArticles();
        articlesViewModel.getAllArticleType();
        articlesViewModel.getAllNewspapers();
    }
}
