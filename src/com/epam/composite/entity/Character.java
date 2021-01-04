package com.epam.composite.entity;

public class Character extends TextComponent {
    private char character;
    private CharacterType type;


    public Character(char character, CharacterType type) {
        super(TextComponentLevel.NOTHING);
        this.character = character;
        this.type = type;
    }

    public CharacterType getType() {
        return type;
    }

    public TextComponentLevel getComponentLevel() {
        return textComponentLevel;
    }

    @Override
    public String buildText() {
        return String.valueOf(character);
    }
}
