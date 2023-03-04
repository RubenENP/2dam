package main.java.game.conditions;

import main.java.game.character.Creature;

public class FindCreatureCondition implements Condition{
    Creature creature;

    public FindCreatureCondition(Creature creature) { this.creature = creature; }

    @Override
    public boolean check() {
        return creature.isViewed();
    }
}
