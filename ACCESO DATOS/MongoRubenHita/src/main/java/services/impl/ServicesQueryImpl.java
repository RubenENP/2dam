package services.impl;

import dao.DaoQuerys;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import services.ServicesQuery;

import java.util.List;

public class ServicesQueryImpl implements ServicesQuery {
    DaoQuerys daoQuerys;

    @Inject
    public ServicesQueryImpl(DaoQuerys daoQuerys) {
        this.daoQuerys = daoQuerys;
    }

    @Override
    public List<Integer> getReadersIdOfAType(String type) {
        return daoQuerys.getReadersIdOfAType(type);
    }

    @Override
    public int avgOfArticlesRateByAReader(String name) {
        return daoQuerys.avgOfArticlesRateByAReader(name);
    }

    @Override
    public String typeOfArticlesMoreRead() {
        return daoQuerys.typeOfArticlesMoreRead();
    }

    @Override
    public List<Integer> numberOfArticlesOfEachTypeOfaNewspaper(String name) {
        return daoQuerys.numberOfArticlesOfEachTypeOfaNewspaper(name);
    }

    @Override
    public List<String> getArticlesDescriptionAndNumberOfReaders() {
        return daoQuerys.getArticlesDescriptionAndNumberOfReaders();
    }

    @Override
    public List<String> nameOfNewspaperOrderByOld() {
        return daoQuerys.nameOfNewspaperOrderByOld();
    }

    @Override
    public List<String> getArticlesOfAType(String type) {
        return daoQuerys.getArticlesOfAType(type);
    }

    @Override
    public List<String> getNumberOfSportsArticlesByNewspaper() {
        return daoQuerys.getNumberOfSportsArticlesByNewspaper();
    }

    @Override
    public String nameOfNewspaperWithHighestNumberOfSportsArticles() {
        return daoQuerys.nameOfNewspaperWithHighestNumberOfSportsArticles();
    }

    @Override
    public List<String> getArticlesWithNoRatingLowerThan3() {
        return daoQuerys.getArticlesWithNoRatingLowerThan3();
    }

    @Override
    public double avgReadArticlesPerReader() {
        return daoQuerys.avgReadArticlesPerReader();
    }

    @Override
    public List<String> getNumberOfReadArticlesPerReader() {
        return daoQuerys.getNumberOfReadArticlesPerReader();
    }

    @Override
    public int articlesWithAvgRatingGreaterThan4() {
        return daoQuerys.articlesWithAvgRatingGreaterThan4();
    }

    @Override
    public List<String> getReadersWithNoReviewLowerThan3() {
        return daoQuerys.getReadersWithNoReviewLowerThan3();
    }

    @Override
    public List<String> getArticlesWithRatingLowerThan5OfNewspaper(String name) {
        return daoQuerys.getArticlesWithRatingLowerThan5OfNewspaper(name);
    }

    @Override
    public List<String> getReadersNotRegisteredAsUsers() {
        return daoQuerys.getReadersNotRegisteredAsUsers();
    }

    @Override
    public List<String> getCarModelsOfPeopleOver35Years() {
        return daoQuerys.getCarModelsOfPeopleOver35Years();
    }
}
