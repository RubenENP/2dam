package main.java.game.conditions;

import main.java.game.object.Item;

public class FindObjectCondition implements Condition{
    Item item;

    public FindObjectCondition(Item item) { this.item = item; }

    @Override
    public boolean check() { return item.isViewed(); }
}
