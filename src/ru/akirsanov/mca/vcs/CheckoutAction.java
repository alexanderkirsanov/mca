package ru.akirsanov.mca.vcs;

import ru.akirsanov.mca.vcs.provider.CheckoutProvider;

/**
 * User: akirsanov
 * Date: 16.02.12 0:52
 */
public class CheckoutAction {
    private final CheckoutProvider provider;

    public CheckoutAction(final CheckoutProvider checkoutProvider) {
        provider = checkoutProvider; 
    }

    public void actionPerformed(String project) {
        provider.doCheckout(project, VcsManager.getInstance(project).getCompositeCheckoutListener());
    }
}
