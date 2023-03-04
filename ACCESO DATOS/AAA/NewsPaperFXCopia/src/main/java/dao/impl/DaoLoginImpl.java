package dao.impl;

import config.ConfigYaml;
import dao.DaoLogin;

import java.nio.file.Paths;

public class DaoLoginImpl implements DaoLogin {

    public String getUser(){
        return Paths.get(ConfigYaml.getInstance().getProperty("user")).toString();
    }

    public String getPasswd(){
        return Paths.get(ConfigYaml.getInstance().getProperty("passwd")).toString();
    }
}
