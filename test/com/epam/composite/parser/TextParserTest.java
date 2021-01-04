package com.epam.composite.parser;

import com.epam.composite.entity.Composite;
import com.epam.composite.exception.ReaderException;
import com.epam.composite.reader.CustomFileReader;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TextParserTest {

    @Test
    public void test() throws ReaderException {
        String text = new CustomFileReader().readFromTxtFile("data/default.txt");
        Composite textComposite = new TextParser().handleRequest(text);
        System.out.println("\n" + textComposite.buildText());
    }
}