package ru.akirsanov.mca.vcs;


import org.jetbrains.annotations.NotNull;

/**
 * User: akirsanov
 * Date: 15.02.12 23:51
 */
public final class VcsKey {
    @NotNull
    private final String keyName;

    public VcsKey(@NotNull final String name) {
        keyName = name;
    }

    @NotNull
    public String getName() {
        return keyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VcsKey vcsKey = (VcsKey)o;

        if (!keyName.equals(vcsKey.keyName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return keyName.hashCode();
    }
}
