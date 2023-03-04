package ui.pantallas.articles;

import domain.model.ArticleType;
import io.reactivex.rxjava3.schedulers.Schedulers;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import servicios.ArticleTypeServicios;
import servicios.ArticlesServicios;
import servicios.NewspapersServicios;

import java.util.List;

public class ArticlesViewModel {
    private final ArticlesServicios articlesServicios;
    private final ArticleTypeServicios articleTypeServicios;
    private final NewspapersServicios newspapersServicios;

    @Inject
    ArticlesViewModel(ArticlesServicios articlesServicios, ArticleTypeServicios articleTypeServicios, NewspapersServicios newspapersServicios) {
        this.articlesServicios = articlesServicios;
        this.articleTypeServicios = articleTypeServicios;
        this.newspapersServicios = newspapersServicios;

        state = new SimpleObjectProperty<>(new ArticlesState(FXCollections.observableArrayList(),
                FXCollections.observableArrayList(), FXCollections.observableArrayList(),null));
    }

    private final ObjectProperty<ArticlesState> state;

    public ReadOnlyObjectProperty<ArticlesState> getState() {
        return state;
    }


    public void getAllArticles() {
        articlesServicios.getAllArticles()
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    ArticlesState articlesState;

                    if (either.isLeft()) {
                        articlesState = new ArticlesState(null, null, null,either.getLeft());
                    } else {
                        articlesState = new ArticlesState(either.get(), state.getValue().articleTypeList(), state.getValue().newspaperList(), null);
                    }

                    state.setValue(articlesState);
                });
    }

    public void getArticleById(String type) {
        articlesServicios.getArticleByName(type)
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    ArticlesState articlesState;

                    if (either.isLeft()) {
                        articlesState = new ArticlesState(null, null, null, either.getLeft());
                    } else {
                        articlesState = new ArticlesState(either.get(), state.getValue().articleTypeList(), state.getValue().newspaperList(), null);
                    }

                    state.setValue(articlesState);
                });
    }

    public void getAllArticleType() {
        articleTypeServicios.getAllArticleType()
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    ArticlesState articlesState;

                    if (either.isLeft()) {
                        articlesState = new ArticlesState(null, null, null, either.getLeft());
                    } else {
                        articlesState = new ArticlesState(state.getValue().articleList(), either.get(), state.get().newspaperList(), null);
                    }

                    state.setValue(articlesState);
                });
    }

    public void getAllNewspapers(){
        newspapersServicios.getAllNewspapers()
                .observeOn(Schedulers.io())
                .subscribe(either -> {
                    ArticlesState articlesState;

                    if (either.isLeft()){
                        articlesState = new ArticlesState(null,null,null,null);
                    } else {
                        articlesState = new ArticlesState(state.getValue().articleList(), state.getValue().articleTypeList(),
                                either.get(), null);
                    }

                    state.setValue(articlesState);
                });
    }

    public void addArticle(String name, String description, String typeName, String newspaperName) {
        int idType = state.getValue().articleTypeList().stream().filter(articleType -> articleType.getName().equals(typeName)).findFirst().orElse(null).getId();

        int idNewspaper = state.getValue().newspaperList().stream().filter(newspaper -> newspaper.getName_newspaper().equals(newspaperName))
                        .findFirst().orElse(null).getId();

        articlesServicios.addArticle(name, description, idType, idNewspaper)
                .observeOn(Schedulers.io())
                .subscribe(either -> {
                    ArticlesState articlesState;

                    if (either.isLeft()){
                        articlesState = new ArticlesState(null,null,null,null);
                    } else {
                        articlesState = new ArticlesState(state.getValue().articleList(), state.getValue().articleTypeList(),
                                state.getValue().newspaperList(), null);
                    }

                    state.setValue(articlesState);
                });
    }
}
