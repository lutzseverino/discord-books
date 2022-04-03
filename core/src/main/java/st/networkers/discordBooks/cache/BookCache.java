package st.networkers.discordBooks.cache;

import st.networkers.discordBooks.book.Book;

public interface BookCache {
    void add(String name, Book book);

    Book get(String name);

    void remove(String name);

    void remove(Book book);
}
