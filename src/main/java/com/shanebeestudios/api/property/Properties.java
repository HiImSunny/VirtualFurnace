package com.shanebeestudios.api.property;

import org.bukkit.*;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Base property class
 */
public abstract class Properties implements Keyed {

    static final Map<NamespacedKey, Properties> KEY_MAP = new HashMap<>();

    final NamespacedKey key;

    Properties(NamespacedKey key) {
        this.key = key;
        KEY_MAP.put(key, this);
    }

    /**
     * Get the key associated with this property
     *
     * @return Key associated with this property
     */
    @Override
    public @NotNull NamespacedKey getKey() {
        return this.key;
    }

}
