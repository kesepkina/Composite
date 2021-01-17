package com.epam.composite.service;

import com.epam.composite.entity.TextComponent;
import com.epam.composite.entity.TextComposite;
import com.epam.composite.exception.ReaderException;
import com.epam.composite.parser.TextParser;
import com.epam.composite.reader.TextFileReader;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Locale;

public class TextServiceTest {

    TextService service = new TextService();
    TextComposite textComposite;

    @BeforeMethod
    public void fillComposite() throws ReaderException {
        String text = new TextFileReader().readFromTxtFile("data/default.txt");
        textComposite = new TextParser().handleRequest(text);
    }

    @Test
    public void testSortParagraphsByNumberOfSentences() {
        System.out.println("---Before:----------\n" + textComposite.buildText());
        service.sortParagraphsByNumberOfSentences(textComposite);
        System.out.println("\n---After:----------\n" + textComposite.buildText());
    }

    @Test
    public void testFindSentencesWithTheLargestWord() {
        List<TextComposite<?>> sentences = service.findSentencesWithTheLargestWord(textComposite);
        System.out.println("Sentences with the largest words:");
        sentences.forEach(sentence -> System.out.println(sentence.buildText()));
    }

    @Test
    public void testDeleteSentencesWithNumberOfWordsLessThen() {
        System.out.println("---Before:----------\n" + textComposite.buildText());
        service.deleteSentencesWithNumberOfWordsLessThen(textComposite, 20);
        System.out.println("\n---After:----------\n" + textComposite.buildText());
    }

    @Test
    public void testFindAllRepeatingWords() {
        List<TextComposite<TextComponent>> repeatingWords = service.findAllRepeatingWords(textComposite);
        System.out.println("Repeating words:");
        repeatingWords.forEach(word -> System.out.println(word.buildText().toLowerCase(Locale.ROOT) + " repeats " + service.calcNumberOfWordRepeats(textComposite, word) + " times"));
    }
}