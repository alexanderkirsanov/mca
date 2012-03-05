package ru.kirsanov.mca.vcs.provider;

import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.wc.ISVNOptions;
import org.tmatesoft.svn.core.wc.SVNUpdateClient;
import org.tmatesoft.svn.core.wc.SVNWCUtil;
import ru.kirsanov.mca.vcs.ConfigManager.ConfigurationProvider;
import ru.kirsanov.mca.communication.entity.RepositoryInfo;

import java.io.File;

/**
 * User: akirsanov
 * Date: 02.03.12 1:11
 */
public class CheckoutProviderFactory {
    private static CheckoutProviderFactory instance;
    private ConfigurationProvider configurationProvider = ConfigurationProvider.getInstance();

    public static CheckoutProviderFactory getInstance() {
        if (instance == null) {
            instance = new CheckoutProviderFactory();

        }
        return instance;
    }

    private CheckoutProviderFactory() {
    }

    public CheckoutProvider createSVNCheckoutProvider(RepositoryInfo info) throws SVNException {
        DAVRepositoryFactory.setup();
        ISVNOptions options = SVNWCUtil.createDefaultOptions(true);
        SVNURL url = SVNURL.parseURIDecoded(info.url);
        ISVNAuthenticationManager manager = SVNWCUtil.createDefaultAuthenticationManager(info.userName, info.password);
        configurationProvider.registerProjectOptions(info.projectName, options);
        configurationProvider.registerVcsUrl(info.projectName, url);
        configurationProvider.registerSvnAuthenticationManager(info.projectName, manager);
        SVNUpdateClient client = new SVNUpdateClient(manager, options);
        File file = new File(ConfigurationProvider.getPath() + File.separator + info.projectName);
        return new SvnCheckoutProvider(client, url, file);
    }


}
