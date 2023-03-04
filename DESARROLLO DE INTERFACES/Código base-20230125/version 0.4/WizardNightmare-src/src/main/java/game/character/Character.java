package main.java.game.character;

import main.java.game.actions.Attack;
import main.java.game.actions.PhysicalAttack;
import main.java.game.Domain;
import main.java.game.spell.SpellUnknowableException;
import main.java.game.spell.Spell;
import main.java.game.spellContainer.Knowledge;
import main.java.game.spellContainer.Memory;
import main.java.game.character.exceptions.CharacterKilledException;
import main.java.game.util.Value;
import main.java.game.util.ValueOverMaxException;
import main.java.game.util.ValueUnderMinException;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Wizard's attributes and related data.
 *
 */
public abstract class Character {

    String name;
    final Domain domain;
    final Value life;
    Knowledge memory;
    ArrayList<Attack> attacks;

    /**
     * @param n name
     * @param l Initial life
     * @param lm Maximum life
     */
    public Character(String n, Domain d, int l, int lm, int hit) {
        name = n;
        domain = d;
        life = new Value(l, 0, lm);
        memory = new Memory();
        attacks = new ArrayList<>();
        attacks.add(new PhysicalAttack(hit));
    }

    public Domain getDomain() { return domain; }

    //Life
    public String lifeInfo(){ return getClass().getSimpleName() + " -> " + life; }
    public int getLife() { return life.getValue(); }
    public int getLifeMax() {
        return life.getMaximum();
    }
    public void upgradeLifeMax(int m) {
        life.increaseMaximum(m);
    }
    public void recoverLife(int v) throws ValueOverMaxException { life.increaseValue(v); }
    public void drainLife(int v) throws CharacterKilledException {
        try {
            life.decreaseValue(v);
        } catch (ValueUnderMinException e) {
            life.setToMinimum();
            throw new CharacterKilledException();
        }
    }

    public abstract int protect(int damage, Domain domain);


    //Spells
    public Knowledge getMemory() { return memory; }
    public void addSpell(Spell spell) throws SpellUnknowableException {
        if(spell instanceof Attack)
            attacks.add((Attack) spell);
        memory.add(spell);
    }
    public Spell getSpell(int index){ return memory.get(index); }
    public boolean existsSpell(Spell spell) { return memory.exists(spell); }


    //Attacks
    public Iterator<Attack> getAttacksIterator() { return attacks.iterator(); }
    public int getNumberOfAttacks() { return attacks.size(); }
    public Attack getAttack(int index) { return attacks.get(index); }




}
