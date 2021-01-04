package com.epam.composite.entity;

public abstract class Component {
    protected ComponentLevel componentLevel;

    protected Component(ComponentLevel componentLevel) {
        this.componentLevel = componentLevel;
    }

    abstract String buildText();
}
