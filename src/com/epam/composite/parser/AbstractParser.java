package com.epam.composite.parser;

import com.epam.composite.entity.Composite;

public abstract class AbstractParser {

    private AbstractParser successor;

    protected AbstractParser(AbstractParser successor) {
        this.successor = successor;
    }

    protected AbstractParser getSuccessor() {
        return successor;
    }

    public abstract Composite handleRequest(String data);
}
