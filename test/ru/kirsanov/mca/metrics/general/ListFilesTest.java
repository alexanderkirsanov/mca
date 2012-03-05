package ru.kirsanov.mca.metrics.general;

import org.junit.Test;
import ru.kirsanov.mca.vcs.ConfigManager.ConfigurationProvider;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static junit.framework.Assert.assertEquals;

/**
 * User: akirsanov
 * Date: 05.03.12 23:30
 */
public class ListFilesTest {
    @Test
    public void testVisitFile() throws Exception {
        String project = "qwqw";
        Path startingDir = Paths.get(ConfigurationProvider.getPath() + File.separator + project);
        FileList fileList = new FileList();
        ListFiles pf = new ListFiles(fileList);
        Files.walkFileTree(startingDir, pf);
        assertEquals(54, pf.getFileList().getJarFiles().size());
        assertEquals(1515,  pf.getFileList().getJavaFiles().size());
    }
}

