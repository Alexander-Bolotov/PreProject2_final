package src.main.dao;

import src.main.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDAO{
    private Connection connection;

    public UserJdbcDAO(Connection connection) {
        this.connection = connection;
    }

    public List<User> getAllUser() {
        List<User> userList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userList.add(new User(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getString("password")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public User getUserByName(String name) {
        User userResult = new User();
        try (PreparedStatement preparedStatement = connection.prepareStatement("select * from users where name= ?")) {
            preparedStatement.setString(1, name);
            ResultSet result = preparedStatement.executeQuery();
            result.next();
            userResult.setId(result.getLong("id"));
            userResult.setName(result.getString("name"));
            userResult.setPassword(result.getString("password"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userResult;
    }

    public boolean nameIsExist(String name) {
        return getAllUser()
                .stream()
                .map(User::getName)
                .anyMatch(x -> x.equals(name));
    }

    public boolean idIsExist(long id) {
        return getAllUser()
                .stream()
                .map(User::getId)
                .anyMatch(x -> x.equals(id));
    }

    public boolean addUser(User user) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (name, password) VALUES (?, ?)")) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
            return  true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void deleteUserByName(String name) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE name= ?")) {
            preparedStatement.setString(1, name);
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteUserById(long id) {
        try(PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id =?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void editeUser(User user) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET name=?, password=? where id LIKE ?")) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setLong(3, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserById(Long id) {
        User user = new User();
        try (PreparedStatement preparedStatement = connection.prepareStatement("select * from users where id= ?")) {
            preparedStatement.setLong(1, id);
            ResultSet result = preparedStatement.executeQuery();
            result.next();
            user.setId(result.getLong("id"));
            user.setName(result.getString("name"));
            user.setPassword(result.getString("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public String getRoleByName(String name) {
        return null;
    }


}