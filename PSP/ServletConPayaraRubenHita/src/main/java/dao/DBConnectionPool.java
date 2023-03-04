package dao;

import config.Configuracion;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
public class DBConnectionPool {

    private DataSource hirakiDatasource = null;

    private Configuracion config;

    @Inject
    public DBConnectionPool(Configuracion config) {
        this.config = config;
    }

    public void cargarPool()
    {
        hirakiDatasource = getDataSourceHikari();
    }

    public Connection getConnection() throws SQLException {

        Connection connection;

        connection = hirakiDatasource.getConnection();

        return connection;
    }


    private DataSource getDataSourceHikari() {
        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setJdbcUrl(this.config.getRuta());
        hikariConfig.setUsername(this.config.getUser());
        hikariConfig.setPassword(this.config.getPassword());
        hikariConfig.setDriverClassName(this.config.getDriver());
        hikariConfig.setMaximumPoolSize(1);

        hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        return new HikariDataSource(hikariConfig);
    }

    public DataSource getDataSource() {
        return hirakiDatasource;
    }

    public void cerrarConexion(Connection connection) {
        try {
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @PreDestroy
    public void closePool() {
        ((HikariDataSource) hirakiDatasource).close();
    }

}
