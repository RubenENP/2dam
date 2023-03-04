package model;

public class Newspaper {
    private int id;
    private String nombre;
    private double precio;
    private String director;

    public Newspaper(String fileLine){
        String[] charArray = fileLine.split(";");
        this.id = Integer.parseInt(charArray[0]);
        this.nombre = charArray[1];
        this.precio = Double.parseDouble(charArray[2]);
        this.director = charArray[3];
    }

    public String toStringTextFile(){
        return id + ";" + nombre + ";" + precio + ";" +director;
    }
}
