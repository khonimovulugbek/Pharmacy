package com.example.backend.usermanager;

import com.example.backend.container.ComponentContainer;

public class UserController {

    public void addUser(User user) {
       ComponentContainer.userService.addUser(user.getName(), user.getSurname(), user.getMiddlename(), user.getPhone_number(), user.getEmail(), user.getAddress(),user.getUserName(),user.getPassword());
    }

    public boolean checkUser(String log, String pass) {
        return ComponentContainer.userService.checkUser(log,pass);
    }
    public boolean checkAdmin(String log, String pass) {
        return ComponentContainer.userService.checkAdmin(log,pass);
    }

    public void removeUser(String userName) {
        ComponentContainer.userService.removeUser(userName);
    }
}
