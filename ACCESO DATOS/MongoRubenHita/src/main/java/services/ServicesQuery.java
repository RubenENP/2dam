package services;

import java.util.List;

public interface ServicesQuery {
    List<Integer> getReadersIdOfAType(String type);
    int avgOfArticlesRateByAReader(String name);
    String typeOfArticlesMoreRead();
    List<Integer> numberOfArticlesOfEachTypeOfaNewspaper(String name);
    List<String> getArticlesDescriptionAndNumberOfReaders();
    List<String> nameOfNewspaperOrderByOld();
    List<String> getArticlesOfAType(String type);
    List<String> getNumberOfSportsArticlesByNewspaper();
    String nameOfNewspaperWithHighestNumberOfSportsArticles();
    List<String> getArticlesWithNoRatingLowerThan3();
    double avgReadArticlesPerReader();
    List<String> getNumberOfReadArticlesPerReader();
    int articlesWithAvgRatingGreaterThan4();
    List<String> getReadersWithNoReviewLowerThan3();
    List<String> getArticlesWithRatingLowerThan5OfNewspaper(String name);
    List<String> getReadersNotRegisteredAsUsers();
    List<String> getCarModelsOfPeopleOver35Years();
}
