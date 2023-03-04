package main.java.game.object;

import main.java.game.actions.Attack;
import main.java.game.character.Character;
import main.java.game.Domain;
import main.java.game.character.exceptions.CharacterKilledException;

public class Weapon extends Item implements Attack {

    public Weapon(int v) { super(Domain.NONE, v); }

    @Override
    public int getDamage() { return value.getValue(); }

    @Override
    public void attack(Character character) throws CharacterKilledException {
        character.drainLife(value.getValue());
    }

}
