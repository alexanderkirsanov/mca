package ru.akirsanov.mca.vcs;

import java.util.HashMap;
import java.util.Map;

/**
 * User: akirsanov
 * Date: 16.02.12 1:02
 */
public class VcsManager {

    private final String project;
    private static final Map<String,VcsManager> map = new HashMap<String, VcsManager>();

    public static VcsManager getInstance(String project) {
        VcsManager manager = map.get(project);
        if (manager == null){
            manager =  new VcsManager(project);
            map.put(project, manager);
        }
        return manager;
    }

    private VcsManager(String project) {
        this.project = project;
    }

    public CheckoutProvider.Listener getCompositeCheckoutListener() {
        return new CompositeCheckoutListener(this.project);
    }
}
