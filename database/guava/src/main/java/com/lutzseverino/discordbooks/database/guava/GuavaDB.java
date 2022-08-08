package com.lutzseverino.discordbooks.database.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.lutzseverino.discordbooks.book.Book;
import com.lutzseverino.discordbooks.database.BookDB;

import java.util.concurrent.TimeUnit;

public class GuavaDB implements BookDB {
    private final Cache<String, Book> cache;

    public GuavaDB() {
        cache = CacheBuilder.newBuilder()
                .expireAfterWrite(20, TimeUnit.MINUTES)
                .build();
    }

    public GuavaDB(Cache<String, Book> cache) {
        this.cache = cache;
    }

    @Override public Book get(String key) {
        return cache.getIfPresent(key);
    }

    @Override public void set(String key, Book value) {
        cache.put(key, value);
    }
}
