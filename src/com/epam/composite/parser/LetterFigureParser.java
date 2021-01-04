package com.epam.composite.parser;

import com.epam.composite.entity.Character;
import com.epam.composite.entity.CharacterType;
import com.epam.composite.entity.TextComponentLevel;
import com.epam.composite.entity.TextComposite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LetterFigureParser extends AbstractTextParser {

    private static final Pattern FIGURE_PATTERN = Pattern.compile("\\d");

    public LetterFigureParser() {
        super(null);
    }

    @Override
    public TextComposite handleRequest(String data) {
        TextComposite letterFigureTextComposite = new TextComposite(TextComponentLevel.CHARACTER);
        int length = data.length();
        for (int i = 0; i < length; i++) {
            String character = data.substring(i, i + 1);
            Matcher numberMatcher = FIGURE_PATTERN.matcher(character);
            Character nextCharacter;
            if (numberMatcher.matches()) {
                nextCharacter = new Character(character.charAt(0), CharacterType.FIGURE);
            } else {
                nextCharacter = new Character(character.charAt(0), CharacterType.LETTER);
            }
            letterFigureTextComposite.add(nextCharacter);
        }

        return letterFigureTextComposite;
    }
}
