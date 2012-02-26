package ru.akirsanov.mca.vcs.listener;


import ru.akirsanov.mca.vcs.Project;

/**
 * User: akirsanov
 * Date: 19.02.12 16:16
 */
public class CheckoutListener implements VcsCheckoutListener {
    @Override
    public boolean processCheckedOutDirectory(Project project) {
        System.out.println("checked");
        return true;
    }
}


