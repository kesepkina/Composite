package com.epam.composite.entity;

import com.epam.composite.comparator.CompositeComparator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Composite extends Component{

    private final static Logger logger = LogManager.getLogger();
    private List<Component> components = new ArrayList<>();

    public Composite(ComponentLevel componentLevel) {
        super(componentLevel);
    }

    public ComponentLevel getComponentLevel() {
        return componentLevel;
    }

    public void add(Component component) {
        components.add(component);
    }

    public int size() {
        return components.size();
    }

    public void sort(CompositeComparator c) {
        if (componentLevel.equals(ComponentLevel.PARAGRAPH) || componentLevel.equals(ComponentLevel.SENTENCE) || componentLevel.equals(ComponentLevel.LEXEME)) {
            List<Composite> compositeList = new ArrayList<>();
            components.stream().filter(x -> !x.componentLevel.equals(ComponentLevel.NOTHING)).forEach(x -> compositeList.add((Composite) x));
            compositeList.sort(c);
            components.clear();
            components.addAll(compositeList);
        } else {
            logger.error("CompositeComparator doesn't work on this level of composite.");
        }
    }

    @Override
    public String buildText() {
        StringJoiner result = new StringJoiner(componentLevel.getDelimiter(), componentLevel.getPrefix(), "");
        components.forEach( x -> result.add(x.buildText()));
        return result.toString();
    }
}
