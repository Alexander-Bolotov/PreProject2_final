package src.main.service;


import src.main.model.User;
import src.main.util.UserDaoFactory;

import java.util.List;

public class Service {

    private static volatile Service instance;

    private Service() {
    }

    public static Service getInstance() {
        Service result = instance;
        if (result != null) {
            return result;
        }
        synchronized (Service.class){
            if(instance == null){
                instance = new Service();
            }
            return instance;
        }
    }
    public List<User> getAllUsers(){
        return new UserDaoFactory().createDAO().getAllUser();
    }

    public void editeUser(User user){
        new UserDaoFactory().createDAO().editeUser(user);
    }

    public void deleteUserById(long id){
        new UserDaoFactory().createDAO().deleteUserById(id);
    }

    public boolean addUser(User user){
        return new UserDaoFactory().createDAO().addUser(user);
    }

    public  String getRoleByName(String name){
        return new UserDaoFactory().createDAO().getRoleByName(name);
    }
}