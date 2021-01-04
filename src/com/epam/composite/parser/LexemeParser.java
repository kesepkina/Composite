package com.epam.composite.parser;

import com.epam.composite.entity.ComponentLevel;
import com.epam.composite.entity.Composite;

public class LexemeParser extends AbstractParser{

    private static final String DELIMITER = ComponentLevel.LEXEME.getDelimiter();

    public LexemeParser() {
        super(new WordNumberMarkParser());
    }

    @Override
    public Composite handleRequest(String data) {
        String[] lexemes = data.trim().split(DELIMITER);
        Composite lexemeComposite = new Composite(ComponentLevel.LEXEME);

        for (String lexeme :
                lexemes) {
            Composite nextLexeme = super.getSuccessor().handleRequest(lexeme);
            lexemeComposite.add(nextLexeme);
        }

        return lexemeComposite;
    }
}
