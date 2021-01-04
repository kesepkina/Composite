package com.epam.composite.parser;

import com.epam.composite.entity.TextComposite;
import com.epam.composite.exception.ReaderException;
import com.epam.composite.reader.TextFileReader;
import org.testng.annotations.Test;

public class TextParserTest {

    @Test
    public void test() throws ReaderException {
        String text = new TextFileReader().readFromTxtFile("data/default.txt");
        TextComposite textComposite = new TextParser().handleRequest(text);
        System.out.println("\n" + textComposite.buildText());
    }
}