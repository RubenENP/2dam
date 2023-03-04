package main.java.game;

import main.java.game.demiurge.Demiurge;
import main.java.game.demiurge.DungeonConfiguration;

public interface DungeonLoader {

    public void load(Demiurge demiurge, DungeonConfiguration dungeonConfiguration);

}
