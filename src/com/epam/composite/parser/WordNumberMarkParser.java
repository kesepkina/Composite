package com.epam.composite.parser;

import com.epam.composite.entity.Character;
import com.epam.composite.entity.CharacterType;
import com.epam.composite.entity.TextComponentLevel;
import com.epam.composite.entity.TextComposite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordNumberMarkParser extends AbstractTextParser {

    private static final Pattern PUNCTUATION_MARK_PATTERN = Pattern.compile("[\\p{Punct}&&[^']]");

    public WordNumberMarkParser() {
        super(new LetterFigureParser());
    }

    @Override
    public TextComposite handleRequest(String data) {
        TextComposite wordNumberMarkTextComposite = new TextComposite(TextComponentLevel.WORD);
        Matcher punctuationMarkMatcher = PUNCTUATION_MARK_PATTERN.matcher(data);
        if (punctuationMarkMatcher.find()) {
            int begin = 0;
            int length = data.length();
            do {
                int startIndex = punctuationMarkMatcher.start(0);
                if (startIndex != begin) {
                    String word = data.substring(begin, startIndex);
                    TextComposite wordTextComposite = super.getSuccessor().handleRequest(word);
                    wordNumberMarkTextComposite.add(wordTextComposite);
                }
                Character punctuationMark = new Character(data.charAt(startIndex), CharacterType.PUNCTUATION_MARK);
                wordNumberMarkTextComposite.add(punctuationMark);
                begin = startIndex + 1;
            } while (begin < length && punctuationMarkMatcher.find(begin));

            if (begin < length) {
                String word = data.substring(begin, length);
                TextComposite wordTextComposite = super.getSuccessor().handleRequest(word);
                wordNumberMarkTextComposite.add(wordTextComposite);
            }
        } else {
            TextComposite wordTextComposite = super.getSuccessor().handleRequest(data);
            wordNumberMarkTextComposite.add(wordTextComposite);
        }

        return wordNumberMarkTextComposite;
    }
}
