package ru.akirsanov.mca.vcs;

import ru.akirsanov.mca.vcs.listener.ListenersService;
import ru.akirsanov.mca.vcs.listener.VcsCheckoutListener;
import ru.akirsanov.mca.vcs.provider.CheckoutProvider;

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
    public void checkoutCompleted() {
        for (VcsCheckoutListener listener :ListenersService.getListeners()){
            listener.processCheckedOutDirectory(project);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompositeCheckoutListener that = (CompositeCheckoutListener) o;

        return !(project != null ? !project.equals(that.project) : that.project != null);

    }

    @Override
    public int hashCode() {
        return project != null ? project.hashCode() : 0;
    }
}
