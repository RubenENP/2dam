package model;

import lombok.Getter;

@Getter
public class Article {
    private int id;
    private String title;
    private String description;
    private int idNewspaper;
    private int idType;
    private String author;

    public Article(String fileLine){
        String[] charArray = fileLine.split(";");
        this.id = Integer.parseInt(charArray[0]);
        this.title = charArray[1];
        this.description = charArray[2];
        this.idNewspaper = Integer.parseInt(charArray[3]);
        this.idType = Integer.parseInt(charArray[4]);
        this.author = charArray[5];
    }

    public String toString(){return id+";"+title+";"+description+";"+idNewspaper+";"+idType+";"+author;}
}
