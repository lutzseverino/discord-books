package com.lutzseverino.discordbooks.cache;

import org.jetbrains.annotations.Nullable;

public interface Cache<T> {
    void add(T t);

    @Nullable T get(String id);

    Iterable<T> getAll();

    void remove(String id);

    void remove(T t);
}
