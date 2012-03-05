package ru.kirsanov.mca.vcs.provider;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;
import org.tmatesoft.svn.core.SVNException;

/**
 * User: akirsanov
 * Date: 15.02.12 23:41
 */
public interface CheckoutProvider {

    void doCheckout(@Nullable Listener listener) throws SVNException;

    @NonNls
    String getVcsName();

    interface Listener {
        void checkoutCompleted();
    }
}
