package ru.akirsanov.mca.vcs.provider;

import org.junit.Before;
import org.junit.Test;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNUpdateClient;
import ru.akirsanov.mca.vcs.ConfigManager.ConfigurationProvider;
import ru.akirsanov.mca.vcs.Project;

import java.io.File;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;

/**
 * User: akirsanov
 * Date: 26.02.12 19:50
 */
public class SvnCheckoutProviderTest {

    private String name;
    private SVNUpdateClient svnUpdateClient;
    private SVNURL svnurl;
    private File file;

    @Before
    public void setUp() throws Exception {
        name = "project";
        svnUpdateClient = mock(SVNUpdateClient.class);
    }

//    @Test
//    public void testDoCheckout() throws Exception {
//        String url = "http://jmonkeyengine.googlecode.com/svn/trunk/";
//        String userName = "anonymous";
//        String password = "anonymous";
//        SVNURL svnurl = SVNURL.parseURIDecoded(url);
//        Project project = new Project(name);
//        ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(userName, password);
//        ISVNOptions options = SVNWCUtil.createDefaultOptions(true);
//
//        SVNUpdateClient client = new SVNUpdateClient(authManager, options);
//        File file = new File(ConfigurationProvider.getPath() + File.separator + project.getName());
//
//        SvnCheckoutProvider svnCheckoutProvider = new SvnCheckoutProvider(client, svnurl, file);
//        CheckoutProvider.Listener listener = mock(CheckoutProvider.Listener.class);
//        svnCheckoutProvider.doCheckout(project, listener);
//        verify(listener, times(1)).checkoutCompleted();
//
//    }

    @Test
    public void testDoCheckout() throws Exception {
        String url = "http://jmonkeyengine.googlecode.com/svn/trunk/";
        svnurl = SVNURL.parseURIDecoded(url);
        Project project = new Project(name);
        file = new File(ConfigurationProvider.getPath() + File.separator + project.getName());
        CheckoutProvider.Listener listener = mock(CheckoutProvider.Listener.class);
        SvnCheckoutProvider svnCheckoutProvider = new SvnCheckoutProvider(svnUpdateClient, svnurl, file);
        svnCheckoutProvider.doCheckout(listener);
        verify(listener, times(1)).checkoutCompleted();
        verify(svnUpdateClient, times(1)).doCheckout(svnurl, file, SVNRevision.HEAD, SVNRevision.HEAD, true);

    }

    @Test
    public void testGetVcsName() throws Exception {
        assertEquals("SVN", new SvnCheckoutProvider(svnUpdateClient, svnurl, file).getVcsName());
    }
}
