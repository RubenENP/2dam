package config;

import dao.DBConnectionPool;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener()
public class ListenerConfig implements ServletContextListener {
    DBConnectionPool pool;

    @Inject
    public ListenerConfig(DBConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        pool.closePool();
    }
}
