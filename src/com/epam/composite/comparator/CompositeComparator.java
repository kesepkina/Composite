package com.epam.composite.comparator;

import com.epam.composite.entity.TextComposite;

import java.util.Comparator;

public enum CompositeComparator implements Comparator<TextComposite> {

    COMPONENTS_NUMBER {
        @Override
        public int compare(TextComposite o1, TextComposite o2) {
            int list1Size = o1.size();
            int list2Size = o2.size();
            return Integer.compare(list1Size, list2Size);
        }
    }

}
