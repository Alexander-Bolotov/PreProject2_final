package src.main.model;

import org.hibernate.SessionFactory;


import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbHelper {

    private static volatile DbHelper instance;

    private DbHelper() {
    }

    public static DbHelper getInstance() {
        DbHelper result = instance;
        if (result != null) {
            return result;
        }
        synchronized (DbHelper.class){
            if(instance == null){
                instance = new DbHelper();
            }
            return instance;
        }
    }
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            sessionFactory = createSessionFactory();
        }
        return sessionFactory;
    }

    public static Configuration getConfiguration(){
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/usersdb?serverTimezone=UTC");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "admin");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        return configuration;
    }

    private static SessionFactory createSessionFactory() {
        Configuration configuration = getConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public Connection getConnection(){
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());
            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("usersdb?").          //db name
                    append("user=root&").           //login
                    append("password=admin").       //password
                    append("&serverTimezone=UTC");

            System.out.println("URL: " + url + "\n");

            return DriverManager.getConnection(url.toString());

        } catch (InstantiationException | SQLException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }
}