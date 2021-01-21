package ru.sapronov.jm_pp_2_3_1.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import ru.sapronov.jm_pp_2_3_1.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Ivan Sapronov
 */
public class HibernateConfiguration {
    private static final String URL = "jdbc:mysql://localhost:3306/my_db?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true"
            + "&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String LOGIN = "bestuser";
    private static final String PASSWORD = "bestuser";

    private static SessionFactory sessionFactory;

    public Connection getConnection(){
        Connection connection = null;
        try {
            //Это уже не нужно, т.к. все драйверы JDBC 4.0, обнаруженные в пути к классу, загружаются автоматически
            //Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            System.out.println("Соединение с Б.Д. установлено!");
        } catch (SQLException e) {
            System.out.println("Ошибка соединения с Б.Д.!");
            e.printStackTrace();
        }
        return connection;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL,
                        "jdbc:mysql://localhost:3306/my_db?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true"
                                + "&useLegacyDatetimeCode=false&serverTimezone=UTC");
                settings.put(Environment.USER, "bestuser");
                settings.put(Environment.PASS, "bestuser");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "update");
                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
