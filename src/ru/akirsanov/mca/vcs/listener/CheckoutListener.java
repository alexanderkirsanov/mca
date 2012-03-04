package ru.akirsanov.mca.vcs.listener;


import ru.akirsanov.mca.vcs.Project;

import java.util.HashMap;
import java.util.Map;

/**
 * User: akirsanov
 * Date: 19.02.12 16:16
 */
public class CheckoutListener implements VcsCheckoutListener {
    public static final String DONE = "done";
    Map<String, String> stateMap = new HashMap<String, String>();

    @Override
    public void processCheckedOutDirectory(Project project) {
        stateMap.put(project.getName(), DONE);
    }

    @Override
    public String getState(Project project) {
        return stateMap.get(project.getName());
    }
}


