package ru.kirsanov.mca.metrics.general;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * User: akirsanov
 * Date: 05.03.12 23:22
 */
public class ListFiles
        extends SimpleFileVisitor<Path> {
    private final FileList fileList;

    public ListFiles(FileList fileList) {
        this.fileList = fileList;
    }

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attr) {
        if (attr.isRegularFile()) {
            if (file.toAbsolutePath().toString().endsWith(".java")) {
                fileList.addJava(file.toAbsolutePath().toString());
            } else if (file.toAbsolutePath().toString().endsWith(".jar")) {
                fileList.addJar(file.toAbsolutePath().toString());
            }
        }
        return FileVisitResult.CONTINUE;
    }

    public FileList getFileList() {
        return this.fileList;
    }
}