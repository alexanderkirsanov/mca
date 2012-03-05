package ru.kirsanov.mca.vcs.ConfigManager;

import org.jetbrains.annotations.Nullable;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.wc.ISVNOptions;
import org.tmatesoft.svn.core.wc.SVNUpdateClient;

/**
 * User: akirsanov
 * Date: 29.02.12 0:12
 */
public class ConfigManager {
    @Nullable
    public SVNUpdateClient createSVNUpdateClient(String project) {
        ISVNAuthenticationManager authManager = ConfigurationProvider.getInstance().getSVNAuthenticationManager(project);
        if (authManager == null)
            return null;

        ISVNOptions options = ConfigurationProvider.getInstance().getOptions(project);
        return new SVNUpdateClient(authManager, options);
    }

    @Nullable
    public SVNURL createUrl(String project) {
        return ConfigurationProvider.getInstance().getSvnUrl(project);
    }
}
