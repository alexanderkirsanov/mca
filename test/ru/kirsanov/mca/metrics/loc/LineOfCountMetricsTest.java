package ru.kirsanov.mca.metrics.loc;

import org.junit.Test;

import ru.kirsanov.mca.metrics.general.FileList;
import ru.kirsanov.mca.metrics.general.ListFiles;
import ru.kirsanov.mca.vcs.ConfigManager.ConfigurationProvider;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static junit.framework.Assert.assertEquals;

/**
 * User: akirsanov
 * Date: 07.03.12 0:18
 */
public class LineOfCountMetricsTest {
    @Test
    public void testCalculate() throws Exception {
        String project = "qwqw";
        Path startingDir = Paths.get(ConfigurationProvider.getPath() + File.separator + project);
        FileList fileList = new FileList();
        ListFiles pf = new ListFiles(fileList);
        Files.walkFileTree(startingDir, pf);
        LineOfCountMetrics lineOfCountMetrics = new LineOfCountMetrics(fileList);
        assertEquals(285436, lineOfCountMetrics.calculate());
    }
}
