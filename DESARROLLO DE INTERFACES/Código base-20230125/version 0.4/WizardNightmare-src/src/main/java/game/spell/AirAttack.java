package main.java.game.spell;

import main.java.game.actions.Attack;
import main.java.game.character.Character;
import main.java.game.Domain;
import main.java.game.character.exceptions.CharacterKilledException;

public class AirAttack extends Spell implements Attack {

    public AirAttack() { super(Domain.AIR, 1); }

    @Override
    public int getDamage() { return level.getValue(); }

    @Override
    public void attack(Character character) throws CharacterKilledException {
        character.drainLife(character.protect(level.getValue(), domain));
    }
}
