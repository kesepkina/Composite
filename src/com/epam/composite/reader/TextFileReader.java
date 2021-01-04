package com.epam.composite.reader;

import com.epam.composite.exception.ReaderException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TextFileReader {

    private static final Logger log = LogManager.getLogger();
    private static final String DEFAULT_PATH = "res/data/default.txt";

    public String readFromTxtFile(String filePath) throws ReaderException {

        Path path;

        if (filePath != null) {
            path = Paths.get(filePath);
            if (Files.exists(path)) {
                try {
                    if (Files.size(path) < 0) {
                        log.info("File \"{}\" is empty. Default file was used.", filePath);
                        path = Paths.get(DEFAULT_PATH);
                    }
                } catch (IOException e) {
                    throw new ReaderException(e);
                }
            } else {
                log.info("File \"{}\" doesn't exist. Default file was used.", filePath);
                path = Paths.get(DEFAULT_PATH);
            }
        } else {
            log.info("Inputted filename equals null. Default file was used.");
            path = Paths.get(DEFAULT_PATH);
        }

        String text;
        try {
            text = Files.readString(path);
        } catch (IOException e) {
            throw new ReaderException("error by reading the file " + filePath, e);
        }

        return text;
    }
}
