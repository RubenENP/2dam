package ui.screen.articles;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Article;
import services.ServicesNewspaper;
import ui.common.BaseScreenController;

public class AddArticlesController extends BaseScreenController {

    @FXML
    private TableView<Article> articlesTable;
    @FXML
    private TableColumn<String, Article> idArticleColumn;
    @FXML
    private TableColumn<String, Article> titleArticleColumn;
    @FXML
    private TableColumn<String, Article> descriptionArticleColumn;
    @FXML
    private TableColumn<String, Article> idNewspaperArticleColumn;
    @FXML
    private TableColumn<String, Article> idTypeArticleColumn;
    @FXML
    private TextField titleTxtField;
    @FXML
    private ComboBox<String> newspapersComboBox;
    @FXML
    private ComboBox<String> typesComboBox;
    @FXML
    private TextArea descriptionTxtField;

    ServicesNewspaper servicesNewspaper;

    @Inject
    AddArticlesController(ServicesNewspaper servicesNewspaper) {
        this.servicesNewspaper = servicesNewspaper;
    }

    public void initialize(){
        idArticleColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleArticleColumn.setCellValueFactory(new PropertyValueFactory<>("name_article"));
        descriptionArticleColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        idNewspaperArticleColumn.setCellValueFactory(new PropertyValueFactory<>("id_newspaper"));
        idTypeArticleColumn.setCellValueFactory(new PropertyValueFactory<>("id_type"));
    }

    @Override
    public void principalCargado() {
        setComboBoxes();

        setTable();
    }

    private void setTable() {
        articlesTable.getItems().clear();

//        if (servicesArticles.getAllArticles().isLeft()){
//            articlesTable.getItems().addAll(servicesArticles.getAllArticles().getLeft());
//        } else {
//            showAlert(servicesArticles.getAllArticles().get(), Alert.AlertType.ERROR);
//        }

    }

    private void setComboBoxes() {
        if (servicesNewspaper.getAll().isLeft()){
//            newspapersComboBox.getItems().addAll(servicesNewspaper.getAll().getLeft()
//                    .stream().map(NewspaperHib::getNameNewspaper).toList());
        } else {
//            showAlert(servicesNewspaper.getAll().get(), Alert.AlertType.ERROR);
        }

//        if (servicesArticleType.getAllTypes().isLeft()){
//            typesComboBox.getItems().addAll(servicesArticleType.getAllTypes().getLeft()
//                    .stream().map(ArticleType::getName).toList());
//        } else {
//            showAlert(servicesArticleType.getAllTypes().get(), Alert.AlertType.ERROR);
//        }
    }

    public void addArticle() {
        String title = titleTxtField.getText();

        String description = descriptionTxtField.getText();

        String newspaperName = newspapersComboBox.getSelectionModel().getSelectedItem();

        String articletypeName = typesComboBox.getSelectionModel().getSelectedItem();

//        Either<Article, String> addArticle = servicesArticles.addArticle(title,description,newspaperName,articletypeName);

//        if (addArticle.isLeft()){
//            showAlert(addArticle.getLeft().getName_article()+" added.", Alert.AlertType.INFORMATION);
//            setTable();
//        } else {
//            showAlert(addArticle.get(), Alert.AlertType.ERROR);
//        }
    }

    private void showAlert(String m, Alert.AlertType articleType){
        Alert a = new Alert(articleType);
        a.setContentText(m);
        a.show();
    }
}
