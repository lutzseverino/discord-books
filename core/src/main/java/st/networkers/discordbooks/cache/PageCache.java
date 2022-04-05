package st.networkers.discordbooks.cache;

import st.networkers.discordbooks.book.Book;

import java.util.ArrayList;
import java.util.List;

public class PageCache implements Cache<Book.Page> {
    private final List<Book.Page> pages = new ArrayList<>();

    @Override public void add(Book.Page page) {
        pages.add(page);
    }

    @Override public Book.Page get(String name) {
        return null;
    }

    @Override public Iterable<Book.Page> getAll() {
        return pages;
    }

    @Override public void remove(String name) {

    }

    @Override public void remove(Book.Page page) {

    }
}
