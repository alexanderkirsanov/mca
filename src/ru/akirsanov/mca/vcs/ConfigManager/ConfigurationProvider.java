package ru.akirsanov.mca.vcs.ConfigManager;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.wc.ISVNOptions;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * User: akirsanov
 * Date: 24.02.12 0:51
 */
public class ConfigurationProvider {
    private final Map<String, ISVNAuthenticationManager> projectConfiguration = new HashMap<String, ISVNAuthenticationManager>();
    private final Map<String, ISVNOptions> projectOptions = new HashMap<String, ISVNOptions>();
    private static ConfigurationProvider configurationProvider;
    private final Map<String, SVNURL> svnUrlsCollection = new HashMap<String, SVNURL>();
    private final Map<String, SVNRevision> svnRevisionsCollection = new HashMap<String, SVNRevision>();

    public static ConfigurationProvider getInstance() {
        if (configurationProvider == null) {
            configurationProvider = new ConfigurationProvider();
        }
        return configurationProvider;
    }

    private ConfigurationProvider() {
    }

    @Nullable
    public ISVNAuthenticationManager getSVNAuthenticationManager(String project) {
        return projectConfiguration.get(project);
    }

    public void registerSvnAuthenticationManager(String project, ISVNAuthenticationManager defaultSVNAuthenticationManager) {
        projectConfiguration.put(project, defaultSVNAuthenticationManager);
    }

    public void registerProjectOptions(String project, ISVNOptions options) {
        projectOptions.put(project, options);
    }


    @NotNull
    public ISVNOptions getOptions(String project) {
        ISVNOptions svnOptions = projectOptions.get(project);
        if (svnOptions == null) {
            File path = new File(getPath());
            svnOptions = SVNWCUtil.createDefaultOptions(path.getAbsoluteFile(), true);
        }
        return svnOptions;
    }

    public void registerSvnUrl(String project, SVNURL svnUrl) {
        svnUrlsCollection.put(project, svnUrl);
    }

    @Nullable
    public SVNURL getSvnUrl(String project) {
        return svnUrlsCollection.get(project);
    }

    public void registerRevision(String project, SVNRevision revision) {
        svnRevisionsCollection.put(project, revision);
    }

    @Nullable
    public SVNRevision getRevision(String project) {
        return svnRevisionsCollection.get(project);
    }

    @NotNull
    public static String getPath() {
        final File standard = SVNWCUtil.getDefaultConfigurationDirectory();
        if (System.getProperty("os.name").startsWith("windows")) {
            return standard.getAbsolutePath();
        }
        return standard.getParent() + File.separator + standard.getName() + "_IDEA";
    }

    public void clear() {
        projectConfiguration.clear();
        projectOptions.clear();
        svnRevisionsCollection.clear();
        svnUrlsCollection.clear();
    }

}
