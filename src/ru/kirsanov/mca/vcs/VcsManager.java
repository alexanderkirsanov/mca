package ru.kirsanov.mca.vcs;

import ru.kirsanov.mca.vcs.provider.CheckoutProvider;

import java.util.HashMap;
import java.util.Map;

/**
 * User: akirsanov
 * Date: 16.02.12 1:02
 */
public class VcsManager {

    private final Project project;
    private static final Map<Project,VcsManager> map = new HashMap<Project, VcsManager>();

    public static VcsManager getInstance(Project project) {
        VcsManager manager = map.get(project);
        if (manager == null){
            manager =  new VcsManager(project);
            map.put(project, manager);
        }

        return manager;
    }

    private VcsManager(Project project) {
        this.project = project;
    }

    public CheckoutProvider.Listener getCompositeCheckoutListener() {
        return new CompositeCheckoutListener(this.project);
    }
}

