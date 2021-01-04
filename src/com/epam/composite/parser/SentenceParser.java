package com.epam.composite.parser;

import com.epam.composite.entity.TextComponentLevel;
import com.epam.composite.entity.TextComposite;
public class SentenceParser extends AbstractTextParser {

    private static final String DELIMITER = TextComponentLevel.SENTENCE.getDelimiter();

    public SentenceParser() {
        super(new LexemeParser());
    }

    @Override
    public TextComposite handleRequest(String data) {

        String[] sentences = data.trim().split(DELIMITER);
        TextComposite sentenceTextComposite = new TextComposite(TextComponentLevel.SENTENCE);

        for (String sentence :
                sentences) {
            TextComposite nextSentence = super.getSuccessor().handleRequest(sentence);
            sentenceTextComposite.add(nextSentence);
        }

        return sentenceTextComposite;
    }
}