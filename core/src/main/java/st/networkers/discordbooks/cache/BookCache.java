package st.networkers.discordbooks.cache;

import org.jetbrains.annotations.NotNull;
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

    @Override public void remove(String id) {
        books.remove(id);
    }

    @Override public void remove(@NotNull Book book) {
        books.remove(book.getName());
    }
}
