package st.networkers.discordbooks;

import org.jetbrains.annotations.NotNull;
import st.networkers.discordbooks.book.Book;
import st.networkers.discordbooks.cache.Cache;
import st.networkers.discordbooks.cache.BookCache;

public class DiscordBooks {
    protected final Cache<Book> books = new BookCache();

    /**
     * Registers new books to the cache.
     *
     * @param books the books to add
     */
    public void addBooks(@NotNull Book... books) {
        for (Book book : books)
            this.books.add(book);
    }

    /**
     * Registers a new book to the cache.
     *
     * @param book the book to add
     */
    public void addBook(Book book) {
        addBooks(book);
    }

    /**
     * @param name the name of the book
     * @return the book with the given name
     */
    public Book getBook(String name) {
        return books.get(name);
    }

    /**
     * @return the book cache
     */
    public Cache<Book> getBooks() {
        return books;
    }
}
