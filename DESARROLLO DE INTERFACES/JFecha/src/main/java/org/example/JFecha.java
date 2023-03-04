package org.example;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class JFecha {
    private LocalDate fecha;

    private int day, month, year;

    private static Panel panel;

    JComboBox<Integer> comboDay;
    JComboBox<String> comboMonth;
    JTextField comboYear;

    JFecha(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;

        String fechaText = day+"/"+month+"/"+year;

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        fecha = LocalDate.parse(fechaText, dateTimeFormatter);

        setPanel();
    }

    JFecha(){

    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public Panel getPanel() {
        return panel;
    }

    public void addListener(String dia, String mes, int año){
        /*Aqui comprobamos las cozas si estan bien o mal y tiramos un
        exception ezzzeeeeeeeeee EJEJEJJEJEJEJ*/

        int diaInt = Integer.parseInt(dia);
        int mesInt = buscarMes(mes);
    }

    private int buscarMes(String mes) {
        switch (mes){
            case "Enero":
                return 1;
            case "Febrero":
                return 2;
            case "Marzo":
                return 3;
            case "Abril":
                return 4;
            case "Mayo":
                return 5;
            case "Junio":
                return 6;
            case "Julio":
                return 7;
            case "Agosto":
                return 8;
            case "Septiembre":
                return 9;
            case "Octubre":
                return 10;
            case "Noviembre":
                return 11;
            case "Diciembre":
                return 12;
            default:
                return 0;
        }
    }

    private void setPanel() {
        panel = new Panel(new GridLayout(2,3));
        JLabel labelDay = new JLabel("Day");
        JLabel labelMonth = new JLabel("Month");
        JLabel labelYear = new JLabel("Year");
        comboDay = new JComboBox<>();
        comboMonth = new JComboBox<>();
        comboYear = new JTextField();
        comboYear.setFont(comboYear.getFont().deriveFont(20f));

        setBoxes();

        panel.add(labelDay);
        panel.add(labelMonth);
        panel.add(labelYear);
        panel.add(comboDay);
        panel.add(comboMonth);
        panel.add(comboYear);
    }

    private void setBoxes() {
        comboMonth.addItem("Enero");
        comboMonth.addItem("Febrero");
        comboMonth.addItem("Marzo");
        comboMonth.addItem("Abril");
        comboMonth.addItem("Mayo");
        comboMonth.addItem("Junio");
        comboMonth.addItem("Julio");
        comboMonth.addItem("Agosto");
        comboMonth.addItem("Septiembre");
        comboMonth.addItem("Octubre");
        comboMonth.addItem("Noviembre");
        comboMonth.addItem("Diciembre");

        comboDay.addItem(1);
        comboDay.addItem(2);
        comboDay.addItem(3);
        comboDay.addItem(4);
        comboDay.addItem(5);
        comboDay.addItem(6);
        comboDay.addItem(7);
        comboDay.addItem(8);
        comboDay.addItem(9);
        comboDay.addItem(10);
        comboDay.addItem(11);
        comboDay.addItem(12);
        comboDay.addItem(13);
        comboDay.addItem(14);
        comboDay.addItem(15);
        comboDay.addItem(16);
        comboDay.addItem(17);
        comboDay.addItem(18);
        comboDay.addItem(19);
        comboDay.addItem(20);
        comboDay.addItem(21);
        comboDay.addItem(22);
        comboDay.addItem(23);
        comboDay.addItem(24);
        comboDay.addItem(25);
        comboDay.addItem(26);
        comboDay.addItem(27);
        comboDay.addItem(28);
        comboDay.addItem(29);
        comboDay.addItem(30);
        comboDay.addItem(31);
    }

    private void setFecha(int day, int month, int year){
        this.day = day;
        this.month = month;
        this.year = year;
    }
    private LocalDate getFecha(){
        return fecha;
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setSize(500, 300);
        jFrame.setVisible(true);

        JFecha jFecha = new JFecha(12, 12, 2000);

        jFrame.add(jFecha.getPanel());
    }
}

