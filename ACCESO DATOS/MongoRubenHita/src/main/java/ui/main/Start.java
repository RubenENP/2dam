package ui.main;

import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import javafx.stage.Stage;
import services.ServicesQuery;

import java.util.Scanner;

public class Start {
    @Inject
    ServicesQuery servicesQuery;

    Scanner sc;

    public void start(@Observes @StartupOne Stage stage) {
        sc = new Scanner(System.in);

        boolean close = false;

        while (!close) {
            System.out.println("Write a number \n1-Get the id of the readers of articles of a specific type" +
                    "\n2-Get the average rating of the articles read by a reader, group by newspaper" +
                    "\n3-c. Get the type of articles that are most read\n" +
                    "4-Show a list with the number of articles of each type of the selected newspaper\n" +
                    "5-Get the description and the number of readers of each article\n" +
                    "6-Get the name of the 10 oldest newspapers\n" +
                    "7-Get the articles of a given type, together with the name of the newspaper\n" +
                    "8-Get the number of Sports articles by newspaper\n" +
                    "9-Get the name of the newspaper with highest number of Sports articles\n" +
                    "10-Get the articles with no rating lower than 3\n" +
                    "11-Get the average number of subscriptions per reader\n" +
                    "12-Number of read articles per reader\n" +
                    "13-Number of articles with average rating greater than 4\n" +
                    "14-Readers with no review lower than 3\n" +
                    "15-Get the articles with a rating lower than 5 of a given newspaper, indicating if the reader " +
                    "has rated more than 4 articles with a lower-than-5 rating\n" +
                    "16-Readers that are not registered as users (Use $lookup)  " +
                    "\n-------------------------------------------------------------" +
                    "\n17-Get car models of people over 35 years \n100-close)");

            int page = sc.nextInt();
            switch (page) {
                case 1 -> exercise1();
                case 2 -> exercise2();
                case 3 -> exercise3();
                case 4 -> exercise4();
                case 5 -> exercise5();
                case 6 -> exercise6();
                case 7 -> exercise7();
                case 8 -> exercise8();
                case 9 -> exercise9();
                case 10 -> exercise10();
                case 11 -> exercise11();
                case 12 -> exercise12();
                case 13 -> exercise13();
                case 14 -> exercise14();
                case 15 -> exercise15();
                case 16 -> exercise16();
                case 17 -> exercise17();
                case 100 -> close = true;
                default -> System.out.println("Wrong number");
            }
        }
    }

    private void exercise17() {
        System.out.println(servicesQuery.getCarModelsOfPeopleOver35Years());
    }

    private void exercise16() {
        System.out.println(servicesQuery.getReadersNotRegisteredAsUsers());
    }

    private void exercise15() {
        System.out.println(servicesQuery.getArticlesWithRatingLowerThan5OfNewspaper("Postman"));
    }

    private void exercise14() {
        System.out.println(servicesQuery.getReadersWithNoReviewLowerThan3());
    }

    private void exercise13() {
        System.out.println(servicesQuery.articlesWithAvgRatingGreaterThan4());
    }

    private void exercise12() {
        System.out.println(servicesQuery.getNumberOfReadArticlesPerReader());
    }

    private void exercise11() {
        System.out.println(servicesQuery.avgReadArticlesPerReader());
    }

    private void exercise10() {
        System.out.println(servicesQuery.getArticlesWithNoRatingLowerThan3());
    }

    private void exercise9() {
        System.out.println(servicesQuery.nameOfNewspaperWithHighestNumberOfSportsArticles());
    }

    private void exercise8() {
        System.out.println(servicesQuery.getNumberOfSportsArticlesByNewspaper());
    }

    private void exercise7() {
        System.out.println(servicesQuery.getArticlesOfAType("Sports"));
    }

    private void exercise6() {
        System.out.println(servicesQuery.nameOfNewspaperOrderByOld());
    }

    private void exercise5() {
        System.out.println(servicesQuery.getArticlesDescriptionAndNumberOfReaders());
    }

    private void exercise4() {
        System.out.println(servicesQuery.numberOfArticlesOfEachTypeOfaNewspaper("Postman"));
    }

    private void exercise3() {
        System.out.println(servicesQuery.typeOfArticlesMoreRead());
    }

    private void exercise2() {
        System.out.println("Write a name");
        System.out.println(servicesQuery.avgOfArticlesRateByAReader("juan"));
    }

    void exercise1() {
        System.out.println("Write a type");
        System.out.println(servicesQuery.getReadersIdOfAType("Sports"));
    }
}
