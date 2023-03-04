package main.java;

import main.java.game.demiurge.Demiurge;
import main.java.game.demiurge.DungeonConfiguration;

import java.io.File;

public interface DungeonLoaderXML {

    void load(Demiurge demiurge, DungeonConfiguration dungeonConfiguration, File file);

    void save(Demiurge demiurge, DungeonConfiguration dungeonConfiguration, File file);

}
