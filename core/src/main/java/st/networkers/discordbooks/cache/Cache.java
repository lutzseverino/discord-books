package st.networkers.discordbooks.cache;

import st.networkers.discordbooks.book.Book;

public interface Cache<T> {
    void add(T t);

    Book get(String name);

    void remove(String name);

    void remove(T t);
}
