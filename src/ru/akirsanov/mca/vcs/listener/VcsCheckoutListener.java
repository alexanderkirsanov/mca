package ru.akirsanov.mca.vcs.listener;

import org.jetbrains.annotations.Nullable;
import ru.akirsanov.mca.vcs.Project;

/**
 * User: akirsanov
 * Date: 19.02.12 16:10
 */
public interface VcsCheckoutListener {
    void processCheckedOutDirectory(final Project project);

    @Nullable
    String getState(final Project project);
}
