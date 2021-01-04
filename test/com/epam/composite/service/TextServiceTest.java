package com.epam.composite.service;

import com.epam.composite.entity.Composite;
import com.epam.composite.exception.ReaderException;
import com.epam.composite.parser.TextParser;
import com.epam.composite.reader.CustomFileReader;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TextServiceTest {

    TextService service = new TextService();
    Composite textComposite;

    @BeforeMethod
    public void test() throws ReaderException {
        String text = new CustomFileReader().readFromTxtFile("data/default.txt");
        textComposite = new TextParser().handleRequest(text);
    }

    @Test
    public void testSortParagraphsByNumberOfSentences() {
        System.out.println("---Before:----------\n" + textComposite.buildText());
        service.sortParagraphsByNumberOfSentences(textComposite);
        System.out.println("\n---After:----------\n" + textComposite.buildText());
    }
}