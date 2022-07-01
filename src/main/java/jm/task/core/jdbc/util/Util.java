package jm.task.core.jdbc.util;
import com.fasterxml.classmate.AnnotationConfiguration;
import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.Service;
import org.hibernate.service.ServiceRegistry;
import javax.persistence.Entity;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Util {
    private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/mysql";
    private static final String LOGIN = "root1";
    private static final String PASSWORD = "root1";
    private static Connection connection = null;

    private static SessionFactory sessionFactory;
    private Util() {
    }
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties properties = new Properties();
//                properties.setProperty("hibernate.connection.url", DATABASE_URL);
//                properties.setProperty("dialect" , "org.hibernate.dialect.MySQLDialect");
//                properties.setProperty("hibernate.connection.username" , LOGIN);
//                properties.setProperty("hibernate.connection.password" , PASSWORD);
//                properties.setProperty("hibernate.connection.driver_class" , DATABASE_DRIVER);
                properties.put(Environment.URL, DATABASE_URL);
                properties.put(Environment.DIALECT , "org.hibernate.dialect.MySQLDialect");
                properties.put(Environment.USER , LOGIN);
                properties.put(Environment.PASS , PASSWORD);
                properties.put(Environment.DRIVER , DATABASE_DRIVER);
                properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS , "thread");

                configuration.setProperties(properties);
                configuration.addAnnotatedClass(User.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

//                StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
//                sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }



    public static Connection connect() {
        try {
            Class.forName(DATABASE_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, LOGIN, PASSWORD);
            //System.out.println("Соединение с базой данных установлено!");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}


