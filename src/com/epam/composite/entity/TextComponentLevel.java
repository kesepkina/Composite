package com.epam.composite.entity;

public enum TextComponentLevel {
    PARAGRAPH ("\n\t", "\t"),
    SENTENCE (" ", ""),
    LEXEME (" ", ""),
    WORD ("", ""),
    CHARACTER ("", ""),
    NOTHING("", "");

    private final String delimiter;
    private final String prefix;

    TextComponentLevel(String delimiter, String prefix) {
        this.delimiter = delimiter;
        this.prefix = prefix;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public String getPrefix() {
        return prefix;
    }
}
