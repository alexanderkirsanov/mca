package ru.akirsanov.mca.vcs.listener;


/**
 * User: akirsanov
 * Date: 19.02.12 16:16
 */
public class CheckoutListener implements VcsCheckoutListener {
    @Override
    public boolean processCheckedOutDirectory(String project) {
        System.out.println("checked");
        return true;
    }
}


