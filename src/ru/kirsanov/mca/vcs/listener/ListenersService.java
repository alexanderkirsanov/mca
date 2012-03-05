package ru.kirsanov.mca.vcs.listener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * User: akirsanov
 * Date: 19.02.12 16:33
 */
public class ListenersService {
    private final static List<VcsCheckoutListener> vcsCheckoutListeners;

    static {
        vcsCheckoutListeners = new ArrayList<VcsCheckoutListener>();
    }

    @NotNull
    public static List<VcsCheckoutListener> getListeners() {
        return vcsCheckoutListeners;
    }

    public static void register(@NotNull VcsCheckoutListener listener) {
        vcsCheckoutListeners.add(listener);
    }


}
