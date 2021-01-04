package com.epam.composite.parser;

import com.epam.composite.entity.ComponentLevel;
import com.epam.composite.entity.Composite;

public class ParagraphParser extends AbstractParser {

    private static final String DELIMITER = ComponentLevel.PARAGRAPH.getDelimiter();

    public ParagraphParser() {
        super(new SentenceParser());
    }

    @Override
    public Composite handleRequest(String data) {
        String[] paragraphs = data.trim().split(DELIMITER);
        Composite paragraphsComposite = new Composite(ComponentLevel.PARAGRAPH);

        for (String paragraph :
                paragraphs) {
            Composite nextParagraph = super.getSuccessor().handleRequest(paragraph);
            paragraphsComposite.add(nextParagraph);
        }

        return paragraphsComposite;
    }
}
