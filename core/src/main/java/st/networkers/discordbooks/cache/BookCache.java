package st.networkers.discordbooks.cache;

import st.networkers.discordbooks.book.Book;

import java.util.HashMap;
import java.util.Map;

public class BookCache implements Cache<Book> {
    private final Map<String, Book> books = new HashMap<>();

    @Override public void add(Book book) {
        books.put(book.getName(), book);
    }

    @Override public Book get(String id) {
        return books.get(id);
    }

    @Override public Iterable<Book> getAll() {
        return books.values();
    }

    @Override public void remove(String name) {
        books.remove(name);
    }

    @Override public void remove(Book book) {
        books.remove(book.getName());
    }
}
