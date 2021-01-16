package com.epam.composite.parser;

import com.epam.composite.entity.TextComponentLevel;
import com.epam.composite.entity.TextComposite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser extends AbstractTextParser {

    private static final Pattern SENTENCE = Pattern.compile(".+?([.?!]+\\s)");

    public SentenceParser() {
        super(new LexemeParser());
    }

    @Override
    public TextComposite handleRequest(String data) {

        TextComposite sentenceTextComposite = new TextComposite(TextComponentLevel.SENTENCE);
        Matcher matcher = SENTENCE.matcher(data);

        int endOfLastMatches = 0;
        while (matcher.find()) {
            endOfLastMatches = matcher.end();
            TextComposite nextSentence = super.getSuccessor().handleRequest(data.substring(matcher.start(), endOfLastMatches - 1));
            sentenceTextComposite.add(nextSentence);
        }
        TextComposite nextSentence = super.getSuccessor().handleRequest(data.substring(endOfLastMatches));
        sentenceTextComposite.add(nextSentence);

        return sentenceTextComposite;
    }
}