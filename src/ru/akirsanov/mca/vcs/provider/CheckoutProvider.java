package ru.akirsanov.mca.vcs.provider;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;

/**
 * User: akirsanov
 * Date: 15.02.12 23:41
 */
public interface CheckoutProvider {

    void doCheckout(@NotNull final String project, @Nullable Listener listener);

    @NonNls
    String getVcsName();

    interface Listener {
        void checkoutCompleted();
    }
}
