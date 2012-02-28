package ru.akirsanov.mca.vcs;

import org.junit.Before;
import org.junit.Test;
import org.tmatesoft.svn.core.internal.wc.DefaultSVNAuthenticationManager;
import org.tmatesoft.svn.core.wc.ISVNOptions;
import org.tmatesoft.svn.core.wc.SVNWCUtil;
import ru.akirsanov.mca.vcs.ConfigManager.ConfigurationProvider;

import java.io.File;

import static junit.framework.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mock;

/**
 * User: akirsanov
 * Date: 26.02.12 19:36
 */
public class ConfigurationProviderTest {

    private String project;

    @Before
    public void setUp() throws Exception {
        project = "project";
    }

    @Test
    public void testSVNAuthenticationManager() throws Exception {
        DefaultSVNAuthenticationManager svnAuthenticationManager = mock(DefaultSVNAuthenticationManager.class);
        ConfigurationProvider.getInstance().registerSvnAuthenticationManager(project, svnAuthenticationManager);
        assertEquals(svnAuthenticationManager, ConfigurationProvider.getInstance().getSVNAuthenticationManager(project));
    }

    @Test
    public void testProjectOptions() throws Exception {
        ISVNOptions options = mock(ISVNOptions.class);
        ConfigurationProvider.getInstance().registerProjectOptions(project, options);
        assertEquals(options, ConfigurationProvider.getInstance().getOptions(project));
    }

    @Test
    public void testGetPath() throws Exception {
        final File standard = SVNWCUtil.getDefaultConfigurationDirectory();
        String result;
        if (System.getProperty("os.name").startsWith("windows")) {
            result = standard.getAbsolutePath();
        } else {
            result = standard.getParent() + File.separator + standard.getName() + "_IDEA";
        }
        assertEquals(result, ConfigurationProvider.getPath());
    }
}
