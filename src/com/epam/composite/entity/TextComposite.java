package com.epam.composite.entity;

import com.epam.composite.comparator.CompositeComparator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class TextComposite extends TextComponent {

    private final static Logger logger = LogManager.getLogger();
    private List<TextComponent> components = new ArrayList<>();

    public TextComposite(TextComponentLevel textComponentLevel) {
        super(textComponentLevel);
    }

    public TextComponentLevel getComponentLevel() {
        return textComponentLevel;
    }

    public void add(TextComponent component) {
        components.add(component);
    }

    public int size() {
        return components.size();
    }

    public void sort(CompositeComparator c) {
        if (textComponentLevel.equals(TextComponentLevel.PARAGRAPH) || textComponentLevel.equals(TextComponentLevel.SENTENCE) || textComponentLevel.equals(TextComponentLevel.LEXEME)) {
            List<TextComposite> textCompositeList = new ArrayList<>();
            components.stream().filter(x -> !x.textComponentLevel.equals(TextComponentLevel.NOTHING)).forEach(x -> textCompositeList.add((TextComposite) x));
            textCompositeList.sort(c);
            components.clear();
            components.addAll(textCompositeList);
        } else {
            logger.error("CompositeComparator doesn't work on this level of composite.");
        }
    }

    @Override
    public String buildText() {
        StringJoiner result = new StringJoiner(textComponentLevel.getDelimiter(), textComponentLevel.getPrefix(), "");
        components.forEach( x -> result.add(x.buildText()));
        return result.toString();
    }
}
