package com.epam.composite.service;

import com.epam.composite.comparator.CompositeComparator;
import com.epam.composite.entity.TextComponent;
import com.epam.composite.entity.TextComponentLevel;
import com.epam.composite.entity.TextComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

public class TextService {

    private static final Logger log = LogManager.getLogger();

    public void sortParagraphsByNumberOfSentences(TextComposite<?> text) {
        text.sort(CompositeComparator.COMPONENTS_NUMBER);
    }

    public List<TextComposite<?>> findSentencesWithTheLargestWord(TextComposite text) {
        List<TextComposite<?>> result = new ArrayList<>();
        if (text == null || text.isEmpty()) {
            log.error("Text is empty.");
            return result;
        }
        int lengthOfLargestWord = 0;
        List<TextComposite> paragraphs = text.getComponents();
        for (TextComposite paragraph : paragraphs) {
            List<TextComposite> sentences = paragraph.getComponents();
            for (TextComposite sentence : sentences) {
                List<TextComposite> lexemes = sentence.getComponents();
                for (TextComposite lexeme : lexemes) {
                    List<TextComponent> lexemeComponents = lexeme.getComponents();
                    Optional<TextComposite<TextComponent>> word = lexemeComponents.stream().filter(component -> component.getTextComponentLevel().equals(TextComponentLevel.CHARACTER)).map(component -> (TextComposite<TextComponent>) component).max(CompositeComparator.COMPONENTS_NUMBER);
                    if (word.isPresent()) {
                        int lengthOfWord = word.get().size();
                        if (lengthOfWord > lengthOfLargestWord) {
                            result.clear();
                            result.add(sentence);
                            lengthOfLargestWord = lengthOfWord;
                        } else if (lengthOfWord == lengthOfLargestWord && !result.contains(sentence)) {
                            result.add(sentence);
                        }
                    }
                }
            }
        }
        return result;
    }

    public void deleteSentencesWithNumberOfWordsLessThen(TextComposite text, int minNumberOfWords) {
        List<TextComposite> paragraphs = text.getComponents();
        for (TextComposite paragraph : paragraphs) {
            List<TextComposite> sentences = paragraph.getComponents();
            int iterator = 0;
            for (TextComposite sentence : sentences) {
                long numberOfWords = 0;
                List<TextComposite> lexemes = sentence.getComponents();
                for (TextComposite lexeme : lexemes) {
                    List<TextComponent> lexemeComponents = lexeme.getComponents();
                    numberOfWords += lexemeComponents.stream().filter(component -> component.getTextComponentLevel().equals(TextComponentLevel.CHARACTER)).count();
                }
                if (numberOfWords < minNumberOfWords) {
                    paragraph.remove(iterator);
                } else {
                    iterator++;
                }
            }
        }
    }

    public List<TextComposite<TextComponent>> findAllRepeatingWords(TextComposite text) {
        List<TextComposite<TextComponent>> repeatingWords = new ArrayList<>();
        if (text == null || text.isEmpty()) {
            log.error("Text is empty.");
            return repeatingWords;
        }
        List<String> allWords = new ArrayList<>();
        List<TextComposite> paragraphs = text.getComponents();
        for (TextComposite paragraph : paragraphs) {
            List<TextComposite> sentences = paragraph.getComponents();
            for (TextComposite sentence : sentences) {
                List<TextComposite> lexemes = sentence.getComponents();
                for (TextComposite lexeme : lexemes) {
                    List<TextComponent> lexemeComponents = lexeme.getComponents();
                    List<TextComposite<TextComponent>> words = lexemeComponents.stream().filter(component -> component.getTextComponentLevel().equals(TextComponentLevel.CHARACTER)).map(component -> (TextComposite<TextComponent>) component).collect(Collectors.toList());
                    if (!words.isEmpty()) {
                        for (TextComposite<TextComponent> word :
                                words) {
                            String buildWord = word.buildText().toLowerCase(Locale.ROOT);
                            if (!allWords.contains(buildWord)) {
                                allWords.add(buildWord);
                            } else if (!repeatingWords.contains(word)) {
                                repeatingWords.add(word);
                            }
                        }
                    }
                }
            }
        }
        return repeatingWords;
    }

    public int calcNumberOfWordRepeats(TextComposite text, TextComposite<TextComponent> word) {
        int result = 0;
        if (text == null || text.isEmpty()) {
            log.error("Text is empty.");
            return result;
        }
        List<TextComposite> paragraphs = text.getComponents();
        for (TextComposite paragraph : paragraphs) {
            List<TextComposite> sentences = paragraph.getComponents();
            for (TextComposite sentence : sentences) {
                List<TextComposite> lexemes = sentence.getComponents();
                for (TextComposite lexeme : lexemes) {
                    List<TextComponent> lexemeComponents = lexeme.getComponents();
                    result += lexemeComponents.stream().filter(component -> component.getTextComponentLevel().equals(TextComponentLevel.CHARACTER)).map(component -> (TextComposite<TextComponent>) component).filter(component -> component.equals(word)).count();
                }
            }
        }
        return result;
    }
}
