package ru.akirsanov.mca.vcs;

import java.io.File;

/**
 * User: akirsanov
 * Date: 16.02.12 1:05
 */
public class CompositeCheckoutListener implements CheckoutProvider.Listener {
    private final String project;

    public CompositeCheckoutListener(String project) {
        this.project = project;
    }

    @Override
    public void directoryCheckedOut(File directory, VcsKey vcs) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void checkoutCompleted() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompositeCheckoutListener that = (CompositeCheckoutListener) o;

        if (project != null ? !project.equals(that.project) : that.project != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return project != null ? project.hashCode() : 0;
    }
}
