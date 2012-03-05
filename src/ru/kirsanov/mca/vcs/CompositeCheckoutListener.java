package ru.kirsanov.mca.vcs;

import ru.kirsanov.mca.vcs.listener.ListenersService;
import ru.kirsanov.mca.vcs.listener.VcsCheckoutListener;
import ru.kirsanov.mca.vcs.provider.CheckoutProvider;

/**
 * User: akirsanov
 * Date: 16.02.12 1:05
 */
public class CompositeCheckoutListener implements CheckoutProvider.Listener {
    private final Project project;

    public CompositeCheckoutListener(Project project) {
        this.project = project;
    }

    @Override
    public void checkoutCompleted() {
        for (VcsCheckoutListener listener : ListenersService.getListeners()) {
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
