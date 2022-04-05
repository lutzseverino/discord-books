package st.networkers.discordbooks.cache;

import st.networkers.discordbooks.book.Book;

import java.util.HashMap;
import java.util.Map;

public class PageCache implements Cache<Book.Page> {
    private final Map<String, Book.Page> pages = new HashMap<>();

    @Override public void add(Book.Page page) {
        pages.put(page.getId(), page);
    }

    @Override public Book.Page get(String id) {
        return pages.get(id);
    }

    @Override public Iterable<Book.Page> getAll() {
        return pages.values();
    }

    @Override public void remove(String id) {
        pages.remove(id);
    }

    @Override public void remove(Book.Page page) {
        pages.remove(page.getId());
    }
}
