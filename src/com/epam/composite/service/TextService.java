package com.epam.composite.service;

import com.epam.composite.comparator.CompositeComparator;
import com.epam.composite.entity.ComponentLevel;
import com.epam.composite.entity.Composite;

public class TextService {

    public void sortParagraphsByNumberOfSentences(Composite text) {
        text.sort(CompositeComparator.COMPONENTS_NUMBER);
    }

    public Composite findCompositeWithTheLargestComponent(Composite text, ComponentLevel compositeLevel, ComponentLevel componentLevel) {
        
    }

}
