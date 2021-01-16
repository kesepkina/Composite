package com.epam.composite.entity;

import com.epam.composite.comparator.CompositeComparator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class TextComposite<T extends TextComponent> extends TextComponent {

    private static final Logger logger = LogManager.getLogger();
    private final List<T> components = new ArrayList<>();

    public TextComposite(TextComponentLevel textComponentLevel) {
        super(textComponentLevel);
    }

    public TextComponentLevel getComponentLevel() {
        return textComponentLevel;
    }

    public void add(T component) {
        components.add(component);
    }

    public int size() {
        return components.size();
    }

    public boolean isEmpty() {
        return components.isEmpty();
    }

    public T remove(int index) {
        return components.remove(index);
    }

    public void sort(CompositeComparator c) {
        if (textComponentLevel.equals(TextComponentLevel.PARAGRAPH) || textComponentLevel.equals(TextComponentLevel.SENTENCE) || textComponentLevel.equals(TextComponentLevel.LEXEME)) {
            List<TextComposite<?>> textCompositeList = new ArrayList<>();
            components.stream().filter(x -> !x.textComponentLevel.equals(TextComponentLevel.NOTHING)).forEach(x -> textCompositeList.add((TextComposite) x));
            textCompositeList.sort(c);
            components.clear();
            components.addAll((Collection<? extends T>) textCompositeList);
        } else {
            logger.error("CompositeComparator doesn't work on this level of composite.");
        }
    }

    public List<T> getComponents() {
        return new ArrayList<>(components);
    }

    @Override
    public String buildText() {
        StringJoiner result = new StringJoiner(textComponentLevel.getDelimiter(), textComponentLevel.getPrefix(), "");
        components.forEach( x -> result.add(x.buildText()));
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextComposite<?> that = (TextComposite<?>) o;

        return buildText().toLowerCase(Locale.ROOT).equals(that.buildText().toLowerCase(Locale.ROOT));
    }

    @Override
    public int hashCode() {
        return components.hashCode();
    }
}