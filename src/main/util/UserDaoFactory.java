package src.main.util;

import src.main.dao.UserDAO;
import src.main.dao.UserHibernateDAO;
import src.main.dao.UserJdbcDAO;
import src.main.model.DbHelper;

import java.io.FileNotFoundException;
import java.io.IOException;

public class UserDaoFactory {

    public UserDAO createDAO() {
        ReadProperties readProperties = new ReadProperties();
        String daoType = readProperties.readProperties();
        System.out.println("daoType = " + daoType);
        switch (daoType) {
            case "UserHibernateDAO":
                return new UserHibernateDAO(DbHelper.getSessionFactory().openSession());
            case "UserJdbcDAO":
                return new UserJdbcDAO(DbHelper.getInstance().getConnection());
        }

        return null;
    }
}