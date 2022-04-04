package st.networkers.discordbooks.cache;

import st.networkers.discordbooks.book.Book;

import java.util.HashMap;
import java.util.Map;

public class BookCache implements Cache<Book> {
    public final Map<String, Book> books = new HashMap<>();

    @Override public void add(Book book) {
        books.put(book.getName(), book);
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
