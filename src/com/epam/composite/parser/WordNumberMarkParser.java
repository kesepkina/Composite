package com.epam.composite.parser;

import com.epam.composite.entity.Character;
import com.epam.composite.entity.CharacterType;
import com.epam.composite.entity.ComponentLevel;
import com.epam.composite.entity.Composite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordNumberMarkParser extends AbstractParser {

    private static final Pattern PUNCTUATION_MARK_PATTERN = Pattern.compile("[\\p{Punct}&&[^']]");

    public WordNumberMarkParser() {
        super(new LetterFigureParser());
    }

    @Override
    public Composite handleRequest(String data) {
        Composite wordNumberMarkComposite = new Composite(ComponentLevel.WORD);
        Matcher punctuationMarkMatcher = PUNCTUATION_MARK_PATTERN.matcher(data);
        if (punctuationMarkMatcher.find()) {
            int begin = 0;
            int length = data.length();
            do {
                int startIndex = punctuationMarkMatcher.start(0);
                if (startIndex != begin) {
                    String word = data.substring(begin, startIndex);
                    Composite wordComposite = super.getSuccessor().handleRequest(word);
                    wordNumberMarkComposite.add(wordComposite);
                }
                Character punctuationMark = new Character(data.charAt(startIndex), CharacterType.PUNCTUATION_MARK);
                wordNumberMarkComposite.add(punctuationMark);
                begin = startIndex + 1;
            } while (begin < length && punctuationMarkMatcher.find(begin));

            if (begin < length) {
                String word = data.substring(begin, length);
                Composite wordComposite = super.getSuccessor().handleRequest(word);
                wordNumberMarkComposite.add(wordComposite);
            }
        } else {
            Composite wordComposite = super.getSuccessor().handleRequest(data);
            wordNumberMarkComposite.add(wordComposite);
        }

        return wordNumberMarkComposite;
    }
}
