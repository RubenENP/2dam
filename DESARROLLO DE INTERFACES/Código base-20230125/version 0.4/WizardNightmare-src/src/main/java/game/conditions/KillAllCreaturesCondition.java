package main.java.game.conditions;

import main.java.game.dungeon.Dungeon;
import main.java.game.dungeon.Room;

import java.util.Iterator;

public class KillAllCreaturesCondition implements Condition{
    Dungeon dungeon;

    public KillAllCreaturesCondition(Dungeon dungeon){ this.dungeon = dungeon; }

    @Override
    public boolean check() {
        Iterator it = dungeon.iterator();
        while (it.hasNext()){
            Room room = (Room) it.next();
            if(room.isAlive())
                return false;
        }
        return true;
    }
}
