package ru.akirsanov.mca.vcs.provider;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.tmatesoft.svn.core.SVNException;
import ru.akirsanov.mca.vcs.Project;

/**
 * User: akirsanov
 * Date: 15.02.12 23:41
 */
public interface CheckoutProvider {

    void doCheckout(@NotNull final Project project, @Nullable Listener listener) throws SVNException;

    @NonNls
    String getVcsName();

    interface Listener {
        void checkoutCompleted();
    }
}
