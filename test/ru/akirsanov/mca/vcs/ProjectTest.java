package ru.akirsanov.mca.vcs;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * User: akirsanov
 * Date: 26.02.12 18:17
 */
public class ProjectTest {

    private String name;
    private String newName;

    @Before
    public void setUp() throws Exception {
        name = "project name";
        newName = "newName";
    }

    @Test
    public void testName() throws Exception {

        Project project = new Project(name);
        assertEquals(name, project.getName());
    }

    @Test
    public void testSetName() throws Exception {
        Project project = new Project(name);
        assertEquals(name, project.getName());
        project.setName(newName);
        assertEquals(newName,project.getName());
    }
}
