package ru.akirsanov.mca.vcs.ConfigManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.wc.ISVNOptions;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

/**
 * User: akirsanov
 * Date: 29.02.12 0:23
 */
public class ConfigManagerTest {

    private String project;

    @Before
    public void setUp() throws Exception {
        project = "project";
    }

    @Test
    public void testCreateSVNUpdateClient() throws Exception {
        ConfigManager configManager = new ConfigManager();
        ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager("one", "two");
        ISVNOptions options = SVNWCUtil.createDefaultOptions(true);
        ConfigurationProvider.getInstance().registerSvnAuthenticationManager(project, authManager);
        ConfigurationProvider.getInstance().registerProjectOptions(project, options);
        assertNotNull(configManager.createSVNUpdateClient(project));
    }

    @Test
    public void testCreateSVNUpdateClientWithIncorrectConfigurationShouldReturnNull() throws Exception {
        ConfigManager configManager = new ConfigManager();
        assertNull(configManager.createSVNUpdateClient(project));
    }

    @Test
    public void testGetUrl() throws Exception {
        ConfigManager configManager = new ConfigManager();
        SVNURL url = SVNURL.parseURIDecoded("http://jmonkeyengine.googlecode.com/svn/trunk/");
        ConfigurationProvider.getInstance().registerVcsUrl(project, url);
        assertNotNull(configManager.createUrl(project));
    }


    @Test
    public void testGetUrlShouldReturnNullIfProjectNotFound() throws Exception {
        ConfigManager configManager = new ConfigManager();
        assertNull(configManager.createUrl(project));
    }

    @After
    public void tearDown() {
        ConfigurationProvider.getInstance().clear();
    }

}
