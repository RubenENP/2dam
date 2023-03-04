package dao.impl;

import config.ConfigXML;
import config.Configuration;
import dao.DBConnectionPool;
import dao.DaoFactions;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import model.Factions;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.SQLQueries;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.concurrent.atomic.AtomicInteger;

public class DaoFactionsImpl implements DaoFactions {
    DBConnectionPool dbConnectionPool = new DBConnectionPool(new Configuration());

    public Factions getAll() throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(Factions.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        Unmarshaller unmarshaller = context.createUnmarshaller();
        Path xmlFile= Paths.get(ConfigXML.getInstance().getProperty("xmlReadersPath"));

        return (Factions) unmarshaller.unmarshal(Files.newInputStream(xmlFile));
    }

    public int insert(Factions factions){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dbConnectionPool.getDataSource());

        AtomicInteger rowsAffected = new AtomicInteger();

        try(Connection myConnection = dbConnectionPool.getDataSource().getConnection();
            Statement statement = myConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE)) {

            ResultSet rs;

            rs = statement.executeQuery(SQLQueries.SELECT_FROM_FACTION);

            factions.getFaction().forEach(faction -> {

                try {
                    rs.moveToInsertRow();

                    rs.updateString(1, faction.getName());
                    rs.updateString(2, faction.getContact());
                    rs.updateString(3, faction.getPlanet());
                    rs.updateInt(4, faction.getNumberCS());
                    rs.updateDate(5, Date.valueOf(faction.getDateLastPurchase()));

                    rs.insertRow();

                    rowsAffected.getAndIncrement();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                if (faction.getWeapons() != null){
                    faction.getWeapons().getWeapon().forEach(weapon ->
                            jdbcTemplate.update(SQLQueries.INSERT_WEAPONS, weapon.getName(), weapon.getPrice())

                    try {
                        rs.moveToInsertRow();

                        rs.updateInt(1, we);
                        rs.updateString(2, faction.getContact());
                        rs.updateString(3, faction.getPlanet());
                        rs.updateInt(4, faction.getNumberCS());
                        rs.updateDate(5, Date.valueOf(faction.getDateLastPurchase()));

                        rs.insertRow();

                        rowsAffected.getAndIncrement();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    );


                }
            });

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        dbConnectionPool.closePool();

        return rowsAffected.get();
    }
}
