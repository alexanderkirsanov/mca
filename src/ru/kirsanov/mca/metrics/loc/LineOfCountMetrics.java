package ru.kirsanov.mca.metrics.loc;

import ru.kirsanov.mca.metrics.general.FileList;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * User: akirsanov
 * Date: 07.03.12 0:02
 */
public class LineOfCountMetrics {
    private FileList fileList;

    public LineOfCountMetrics(FileList fileList) {
        this.fileList = fileList;
    }

    public long calculate() {
        long lineCount = 0;
        for (String path : fileList.getJavaFiles())
            try {
                lineCount += LineInFileCounter.calculate(Paths.get(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        return lineCount;
    }
}

