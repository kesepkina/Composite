package com.epam.composite.service;

import com.epam.composite.comparator.CompositeComparator;
import com.epam.composite.entity.TextComponentLevel;
import com.epam.composite.entity.TextComposite;

public class TextService {

    public void sortParagraphsByNumberOfSentences(TextComposite text) {
        text.sort(CompositeComparator.COMPONENTS_NUMBER);
    }

    public TextComposite findCompositeWithTheLargestComponent(TextComposite text, TextComponentLevel compositeLevel, TextComponentLevel textComponentLevel) {
        return null;
    }

}
