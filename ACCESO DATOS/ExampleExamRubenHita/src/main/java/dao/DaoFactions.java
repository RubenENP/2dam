package dao;

import jakarta.xml.bind.JAXBException;
import model.Factions;

import java.io.IOException;

public interface DaoFactions {
    Factions getAll() throws JAXBException, IOException;
}
