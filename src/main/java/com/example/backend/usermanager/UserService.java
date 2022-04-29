package com.example.backend.usermanager;


import com.example.backend.host.Host;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class UserService {
    @SneakyThrows
    public void createUserTable() {
        Host.statement.executeUpdate("create table if not exists getPharmUser\n" +
                "(\n" +
                "    id           serial,\n" +
                "    name         varchar not null,\n" +
                "    surname      varchar not null,\n" +
                "    middlename   varchar,\n" +
                "    phone_number varchar(13),\n" +
                "    email        varchar,\n" +
                "    address      varchar,\n" +
                "    username     varchar not null unique,\n" +
                "    password     varchar not null,\n" +
                "    type         varchar default 'User',\n" +
                "    primary key (id, username)\n" +
                ");");

    }

    @SneakyThrows
    public void addUser(String name, String surname, String middlename, String phone_number, String email, String address, String userName, String password) {
        Host.statement.executeUpdate("insert into getPharmUser(name, surname, middlename, phone_number, email, address, username, password)\n" +
                "VALUES ('" + name + "', '" + surname + "','" + middlename + "','" + phone_number + "','" + email + "','" + address + "','" + userName + "','" + password + "')");
    }

    public boolean checkUser(String userName, String password) {
        for (User user : getUserList()) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    @SneakyThrows
    public List<User> getUserList() {
        List<User> users = new LinkedList<>();
        ResultSet resultSet = Host.statement.executeQuery("select * from getpharmuser");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String middlename = resultSet.getString("middlename");
            String phone_number = resultSet.getString("phone_number");
            String email = resultSet.getString("email");
            String address = resultSet.getString("address");
            String userName = resultSet.getString("userName");
            String password = resultSet.getString("password");
            String type = resultSet.getString("type");
            User user = new User();
            user.setId(id);
            user.setName(name);
            user.setSurname(surname);
            user.setMiddlename(middlename);
            user.setPhone_number(phone_number);
            user.setEmail(email);
            user.setAddress(address);
            user.setUserName(userName);
            user.setPassword(password);
            user.setType(type);
            users.add(user);
        }
        return users;
    }

    public boolean checkAdmin(String userName, String password) {
        for (User user : getUserList()) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password) && user.getType().equals("admin")) {
                return true;
            }
        }

        return false;
    }

    @SneakyThrows
    public void removeUser(String userName) {
        Host.statement.executeUpdate("delete from getpharmuser where username = '" + userName + "'");
    }
}
