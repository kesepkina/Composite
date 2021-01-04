package com.epam.composite.parser;

import com.epam.composite.entity.TextComponentLevel;
import com.epam.composite.entity.TextComposite;

public class LexemeParser extends AbstractTextParser {

    private static final String DELIMITER = TextComponentLevel.LEXEME.getDelimiter();

    public LexemeParser() {
        super(new WordNumberMarkParser());
    }

    @Override
    public TextComposite handleRequest(String data) {
        String[] lexemes = data.trim().split(DELIMITER);
        TextComposite lexemeTextComposite = new TextComposite(TextComponentLevel.LEXEME);

        for (String lexeme :
                lexemes) {
            TextComposite nextLexeme = super.getSuccessor().handleRequest(lexeme);
            lexemeTextComposite.add(nextLexeme);
        }

        return lexemeTextComposite;
    }
}
