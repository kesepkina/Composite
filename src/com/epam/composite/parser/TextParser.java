package com.epam.composite.parser;

import com.epam.composite.entity.TextComposite;

public class TextParser extends AbstractTextParser {

    public TextParser() {
        super(new ParagraphParser());
    }

    @Override
    public TextComposite handleRequest(String data) {
        TextComposite text = super.getSuccessor().handleRequest(data);
        return text;
    }
}
