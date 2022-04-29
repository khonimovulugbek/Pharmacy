package com.example.backend.container;


import com.example.backend.drugmanager.DrugController;
import com.example.backend.drugmanager.DrugService;
import com.example.backend.usermanager.UserController;
import com.example.backend.usermanager.UserService;

public interface ComponentContainer {
    DrugController drugController = new DrugController();
    DrugService drugService = new DrugService();
    UserController userController = new UserController();
    UserService userService = new UserService();


}
