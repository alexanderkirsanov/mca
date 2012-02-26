package ru.akirsanov.mca.vcs.provider;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNUpdateClient;
import ru.akirsanov.mca.vcs.Project;

import java.io.File;

/**
 * User: akirsanov
 * Date: 20.02.12 0:05
 */
public class SvnCheckoutProvider implements CheckoutProvider {
    private SVNUpdateClient client;
    private SVNURL url;
    private File file;

    public SvnCheckoutProvider(SVNUpdateClient client, SVNURL url, File path) {
        this.client = client;
        this.url = url;
        this.file = path;
    }

    @Override
    public void doCheckout(@NotNull Project project, @Nullable Listener listener) throws SVNException {
        client.doCheckout(url, file, SVNRevision.HEAD, SVNRevision.HEAD, true);
        if (listener != null) {
            listener.checkoutCompleted();
        }
    }

    @Override
    public String getVcsName() {
        return "SVN";
    }
}
