package com.epam.composite.entity;

public class Character extends Component{
    private char character;
    private CharacterType type;


    public Character(char character, CharacterType type) {
        super(ComponentLevel.NOTHING);
        this.character = character;
        this.type = type;
    }

    public CharacterType getType() {
        return type;
    }

    public ComponentLevel getComponentLevel() {
        return componentLevel;
    }

    @Override
    public String buildText() {
        return String.valueOf(character);
    }
}
