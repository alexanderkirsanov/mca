package ru.kirsanov.mca.vcs.provider;

import org.jetbrains.annotations.Nullable;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNUpdateClient;

import java.io.File;

/**
 * User: akirsanov
 * Date: 20.02.12 0:05
 */
public class SvnCheckoutProvider implements CheckoutProvider {
    private final SVNUpdateClient client;
    private final SVNURL url;
    private final File file;

    public SvnCheckoutProvider(SVNUpdateClient client, SVNURL url, File path) {
        this.client = client;
        this.url = url;
        this.file = path;
    }

    @Override
    public void doCheckout(@Nullable Listener listener) throws SVNException {
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
