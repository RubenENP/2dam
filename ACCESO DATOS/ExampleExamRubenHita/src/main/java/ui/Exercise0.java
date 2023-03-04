package ui;

import dao.impl.DaoFactionsImpl;
import jakarta.xml.bind.JAXBException;
import java.io.IOException;

public class Exercise0 {

    public static void main(String[] args) throws JAXBException, IOException {
        DaoFactionsImpl daoFactions = new DaoFactionsImpl();

        System.out.println(daoFactions.insert(daoFactions.getAll()));
    }
}