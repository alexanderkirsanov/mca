package ru.kirsanov.mca.metrics.general;

import java.util.ArrayList;

/**
 * User: akirsanov
 * Date: 05.03.12 23:59
 */
public class FileList {
    private final ArrayList<String> javaFiles = new ArrayList<String>();
    private final ArrayList<String> jarFiles = new ArrayList<String>();

    public ArrayList<String> getJavaFiles() {
        return javaFiles;
    }

    public ArrayList<String> getJarFiles() {
        return jarFiles;
    }

    public void addJar(String jar) {
        jarFiles.add(jar);
    }

    public void addJava(String java) {
        javaFiles.add(java);
    }

}
