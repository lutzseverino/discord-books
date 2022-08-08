package com.lutzseverino.discordbooks.database;

import com.lutzseverino.discordbooks.book.Book;

public interface BookDB {
    Book get(String key);

    void set(String key, Book value);
}
