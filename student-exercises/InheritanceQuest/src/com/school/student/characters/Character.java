package com.school.student.characters;

/**
 * Base class for all quest characters. Refactor the class to support subclasses with
 * unique abilities and stats.
 */
public class Character {

    private final String name;
    private int health;

    public Character(String name, int health) {
        this.name = name;
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void receiveDamage(int amount) {
        // TODO: introduce defense modifiers when subclasses override this behavior
        health = Math.max(0, health - amount);
    }

    public String attack() {
        // TODO: subclasses should override and call super.attack() when chaining abilities
        return name + " attacks with a basic strike";
    }
}
