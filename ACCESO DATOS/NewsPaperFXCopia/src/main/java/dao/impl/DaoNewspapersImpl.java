package dao.impl;

import config.ConfigProperties;
import dao.DaoNewspaper;
import lombok.extern.log4j.Log4j2;
import model.Newspaper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class DaoNewspapersImpl implements DaoNewspaper {
    public List<Newspaper> getAll() {
        Path file = Paths.get(ConfigProperties.getInstance().getProperty("newspaperList"));
        List<Newspaper> newspaperList = new ArrayList<>();
        try{
            List<String> newspapers = Files.readAllLines(file);
            newspapers.forEach(newspaper -> newspaperList.add(new Newspaper(newspaper)));
        }catch (IOException e){
            log.error(e.getMessage());
        }

        return newspaperList;
    }

    public boolean addNewspaper(String id, String nombre, String precio, String director){
        Path file = Paths.get(ConfigProperties.getInstance().getProperty("newspaperList"));
        List<Newspaper> newspaperList = getAll();

        try {
            String line = id+";"+nombre+";"+precio+";"+director;

            newspaperList.add(new Newspaper(line));
            List<String> newspaperString = newspaperList.stream().map(Newspaper::toString).toList();

            Files.write(file, newspaperString);
        }catch (IOException e){
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean delete(int id){
        Path file = Paths.get(ConfigProperties.getInstance().getProperty("newspaperList"));
        List<Newspaper> newspaperList = getAll();

        try{
            Newspaper n = newspaperList.stream().filter(newspaper -> newspaper.getId() == id).findFirst().orElse(null);
            newspaperList.remove(n);

            List<String> stringList = new ArrayList<>();

            stringList.addAll(newspaperList.stream().map(Newspaper::toString).toList());

            Files.write(file, stringList);
        }catch (IOException e){
            log.error(e.getMessage());
            return false;
        }
        return true;
    }


}
