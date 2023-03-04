package dao.impl;

import dao.DaoLogin;
import dao.DataBase;
import jakarta.inject.Inject;

public class DaoLoginImpl implements DaoLogin {
    DataBase dataBase;

    @Inject
    DaoLoginImpl(DataBase dataBase){
        this.dataBase = dataBase;
    }

    public String getUser(){
        return dataBase.getUser();
    }

    public String getPasswd(){
        return dataBase.getPasswd();
    }
}
