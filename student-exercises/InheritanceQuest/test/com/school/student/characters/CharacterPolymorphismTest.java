package com.school.student.characters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CharacterPolymorphismTest {


    @Test
    @DisplayName("receiveDamage() never drops below zero health")
    void characterDoesNotGoNegative() {
        Character hero = new Character("Hero", 10);
        hero.receiveDamage(12);
        assertEquals(0, hero.getHealth());
    }

    @Test
    @DisplayName("Mage attack() demonstrates polymorphism")
    void mageAttack() {
        Character mage = new Mage("Lina", 18);
        assertTrue(mage.attack().contains("spark"));
    }
}
