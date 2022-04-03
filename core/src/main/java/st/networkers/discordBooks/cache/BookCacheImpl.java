package st.networkers.discordBooks.cache;

import st.networkers.discordBooks.book.Book;

import java.util.HashMap;
import java.util.Map;

public class BookCacheImpl implements BookCache {
    public final Map<String, Book> books = new HashMap<>();

    @Override public void add(String name, Book book) {
        books.put(name, book);
    }

    @Override public Book get(String name) {
        return books.get(name);
    }

    @Override public void remove(String name) {
        books.remove(name);
    }

    @Override public void remove(Book book) {
        books.remove(book.getName());
    }
}
