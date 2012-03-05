package ru.kirsanov.mca.vcs;

import org.tmatesoft.svn.core.SVNException;
import ru.kirsanov.mca.vcs.provider.CheckoutProvider;

/**
 * User: akirsanov
 * Date: 16.02.12 0:52
 */
public class CheckoutAction {
    private final CheckoutProvider provider;

    public CheckoutAction(final CheckoutProvider checkoutProvider) {
        provider = checkoutProvider;
    }

    public void actionPerformed(Project project) throws SVNException {
        provider.doCheckout(VcsManager.getInstance(project).getCompositeCheckoutListener());
    }
}
