package main.java.game.actions;

import main.java.game.character.Character;
import main.java.game.character.exceptions.CharacterKilledException;

public interface Attack {
    public int getDamage();

    public void attack(Character character) throws CharacterKilledException;
}
