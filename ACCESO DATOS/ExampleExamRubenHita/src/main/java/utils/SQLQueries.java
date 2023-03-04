package utils;

public class SQLQueries {

    public static final String INSERT_FACTION = "INSERT INTO faction (fname, contact, planet, number_controlled_systems, date_last_purchase) values (?, ?, ?, ?, ?)";
    public static final String INSERT_WEAPONS = "INSERT INTO weapons (wname, wprice) values (?, ?)";
    public static final String DELETE_FROM_WEAPONS = "DELETE from weapons WHERE id=?";
    public static final String SELECT_FROM_FACTION = "SELECT * from faction";
}