package ru.akirsanov.mca.vcs.listener;


/**
 * User: akirsanov
 * Date: 19.02.12 16:10
 */
public interface VcsCheckoutListener {
    boolean processCheckedOutDirectory(final String project);
}
