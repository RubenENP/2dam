package ui;

import dao.impl.DaoFactionsImpl;
import dao.impl.DaoWeaponsImpl;
import jakarta.xml.bind.JAXBException;

import java.io.IOException;

public class Exercise1 {
    public static void main(String[] args) throws JAXBException, IOException {
        DaoWeaponsImpl daoWeapons = new DaoWeaponsImpl();

        daoWeapons.delete(1);
    }
}
