package com.epam.composite.parser;

import com.epam.composite.entity.TextComponentLevel;
import com.epam.composite.entity.TextComposite;

public class ParagraphParser extends AbstractTextParser {

    private static final String DELIMITER = TextComponentLevel.PARAGRAPH.getDelimiter();

    public ParagraphParser() {
        super(new SentenceParser());
    }

    @Override
    public TextComposite handleRequest(String data) {
        String[] paragraphs = data.trim().split(DELIMITER);
        TextComposite paragraphsTextComposite = new TextComposite(TextComponentLevel.PARAGRAPH);

        for (String paragraph :
                paragraphs) {
            TextComposite nextParagraph = super.getSuccessor().handleRequest(paragraph);
            paragraphsTextComposite.add(nextParagraph);
        }

        return paragraphsTextComposite;
    }
}
