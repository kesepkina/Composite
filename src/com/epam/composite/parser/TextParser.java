package com.epam.composite.parser;

import com.epam.composite.entity.Composite;

public class TextParser extends AbstractParser{

    public TextParser() {
        super(new ParagraphParser());
    }

    @Override
    public Composite handleRequest(String data) {
        Composite text = super.getSuccessor().handleRequest(data);
        return text;
    }
}
