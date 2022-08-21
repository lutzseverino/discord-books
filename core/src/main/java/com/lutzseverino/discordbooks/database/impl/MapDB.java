package com.lutzseverino.discordbooks.database.impl;

import com.lutzseverino.discordbooks.book.Book;
import com.lutzseverino.discordbooks.database.BookDB;

import java.util.HashMap;
import java.util.Map;


public class MapDB implements BookDB {
    private final Map<String, Book> map = new HashMap<>();

    @Override public Book get(String key) {
        return map.get(key);
    }

    @Override public void set(String key, Book value) {
        map.put(key, value);
    }
}