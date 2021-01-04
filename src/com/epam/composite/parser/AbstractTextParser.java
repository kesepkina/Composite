package com.epam.composite.parser;

import com.epam.composite.entity.TextComposite;

public abstract class AbstractTextParser {

    private AbstractTextParser successor;

    protected AbstractTextParser(AbstractTextParser successor) {
        this.successor = successor;
    }

    protected AbstractTextParser getSuccessor() {
        return successor;
    }

    public abstract TextComposite handleRequest(String data);
}
