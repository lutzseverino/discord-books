package com.lutzseverino.discordbooks;

import com.lutzseverino.discordbooks.book.Book;
import com.lutzseverino.discordbooks.database.BookDB;
import com.lutzseverino.discordbooks.database.impl.MapDB;
import org.jetbrains.annotations.NotNull;

public class DiscordBooks {
    private static BookDB namelessDatabase = new MapDB();
    private static BookDB database = new MapDB();

    public DiscordBooks() {
    }

    public static BookDB getNamelessDatabase() {
        return namelessDatabase;
    }

    public DiscordBooks setNamelessDatabase(BookDB database) {
        namelessDatabase = database;
        return this;
    }

    public static BookDB getDatabase() {
        return database;
    }

    public DiscordBooks setDatabase(BookDB database) {
        DiscordBooks.database = database;
        return this;
    }

    /**
     * @param id the id of the book
     * @return the book with the given id, or null if it doesn't exist
     */
    public static Book getBook(String id) {
        return database.get(id);
    }

    /**
     * Registers books. These will persist
     * as long as they exist in your source,
     * assuming you add them on every startup.
     *
     * @param books the books to add
     */
    public void registerBooks(@NotNull Book... books) {
        for (var book : books)
            database.set(book.getId(), book);
    }
}
