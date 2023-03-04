package model;


import java.util.Objects;

public class Article {
    private int id;
    private String name_article;
    private String description;
    private int id_type;
    private int id_newspaper;

    public Article() {
    }

    public Article(int id, String nameArticle, String description, int idType, int idNewspaper) {
        this.id = id;
        this.name_article = nameArticle;
        this.description = description;
        this.id_type = idType;
        this.id_newspaper = idNewspaper;
    }

    public Article(String name_article, String description, int id_type, int id_newspaper) {
        this.name_article = name_article;
        this.description = description;
        this.id_type = id_type;
        this.id_newspaper = id_newspaper;
    }

    public int getId() {
        return id;
    }

    public String getName_article() {
        return name_article;
    }

    public int getId_type() {
        return id_type;
    }

    public int getId_newspaper() {
        return id_newspaper;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName_article(String name_article) {
        this.name_article = name_article;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId_type(int id_type) {
        this.id_type = id_type;
    }

    public void setId_newspaper(int id_newspaper) {
        this.id_newspaper = id_newspaper;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Article that = (Article) o;
        return Objects.equals(getName_article(), that.getName_article());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName_article());
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", name_article='" + name_article + '\'' +
                ", description='" + description + '\'' +
                ", id_type=" + id_type +
                ", id_newspaper=" + id_newspaper +
                '}';
    }
}
