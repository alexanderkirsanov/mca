package ru.kirsanov.mca.metrics.loc;

import org.mockito.internal.util.reflection.FieldSetter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * User: akirsanov
 * Date: 06.03.12 23:53
 */
public class LineInFileCounter {
    public static int calculate(Path path) throws IOException {
        return Files.readAllLines(path, Charset.defaultCharset()).size();
    }
}
