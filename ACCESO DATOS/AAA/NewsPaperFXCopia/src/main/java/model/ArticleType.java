package model;

import lombok.Getter;

@Getter
public class ArticleType {
    private final int id;
    private final String name;

    public ArticleType(String linea){
        String[] charArray = linea.split(";");
        this.id = Integer.parseInt(charArray[0]);
        this.name = charArray[1];
    }

    public String toString(){
        return id+";"+name;
    }
}
