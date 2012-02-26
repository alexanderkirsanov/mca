package ru.akirsanov.mca.vcs;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * User: akirsanov
 * Date: 26.02.12 18:35
 */
public class VcsManagerTest {
    @Test
    public void testGetInstance() throws Exception {
        Project project = new Project("test");
        assertEquals(new CompositeCheckoutListener(project), VcsManager.getInstance(project).getCompositeCheckoutListener());
    }
}
