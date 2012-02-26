package ru.akirsanov.mca.vcs;

/**
 * User: akirsanov
 * Date: 20.02.12 0:13
 */
public class Project {
    private String name;

    public Project(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
