package com.epam.composite.parser;

import com.epam.composite.entity.ComponentLevel;
import com.epam.composite.entity.Composite;
public class SentenceParser extends AbstractParser {

    private static final String DELIMITER = ComponentLevel.SENTENCE.getDelimiter();

    public SentenceParser() {
        super(new LexemeParser());
    }

    @Override
    public Composite handleRequest(String data) {

        String[] sentences = data.trim().split(DELIMITER);
        Composite sentenceComposite = new Composite(ComponentLevel.SENTENCE);

        for (String sentence :
                sentences) {
            Composite nextSentence = super.getSuccessor().handleRequest(sentence);
            sentenceComposite.add(nextSentence);
        }

        return sentenceComposite;
    }
}