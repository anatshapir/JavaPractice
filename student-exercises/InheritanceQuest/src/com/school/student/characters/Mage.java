package com.school.student.characters;

/**
 * Example subclass that you can modify or replace. Add more unique character types!
 */
public class Mage extends Character {

    public Mage(String name, int health) {
        super(name, health);
    }

    @Override
    public String attack() {
        // TODO: replace with elemental spell logic
        return getName() + " conjures a spark";
    }
}
