package com.epam.composite.entity;

public abstract class TextComponent {
    protected TextComponentLevel textComponentLevel;

    protected TextComponent(TextComponentLevel textComponentLevel) {
        this.textComponentLevel = textComponentLevel;
    }

    abstract String buildText();
}
