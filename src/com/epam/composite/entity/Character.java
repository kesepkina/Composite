package com.epam.composite.entity;

public class Character extends TextComponent {
    private final char value;
    private final CharacterType type;

    public Character(char value, CharacterType type) {
        super(TextComponentLevel.NOTHING);
        this.value = value;
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
        return String.valueOf(value);
    }
}
