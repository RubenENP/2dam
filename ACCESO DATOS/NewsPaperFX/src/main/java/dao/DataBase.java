package dao;

import config.Config;
import model.Newspaper;

import java.util.ArrayList;
import java.util.List;

public class DataBase {
    public Config config;

    public DataBase(){
        this.config = Config.getInstance();
    }

    public String getUser(){
        return config.getUser();
    }

    public String getPasswd(){
        return config.getPasswd();
    }
}
