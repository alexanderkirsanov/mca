package ru.akirsanov.mca.vcs.listener;

import ru.akirsanov.mca.vcs.Project;

/**
 * User: akirsanov
 * Date: 19.02.12 16:10
 */
public interface VcsCheckoutListener {
    boolean processCheckedOutDirectory(final Project project);
}
