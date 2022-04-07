package st.networkers.discordbooks;

import org.jetbrains.annotations.NotNull;
import st.networkers.discordbooks.book.Book;
import st.networkers.discordbooks.cache.Cache;
import st.networkers.discordbooks.cache.BookCache;
import st.networkers.discordbooks.cache.PageCache;

public class DiscordBooks {
    protected final Cache<Book> books = new BookCache();
    protected final Cache<Book.Page> pages = new PageCache();

    public void addBooks(@NotNull Book... books) {
        for (Book book : books) {
            this.books.add(book);
            book.getPages().forEach(this.pages::add);
        }
    }

    public void addBook(Book book) {
        addBooks(book);
    }

    public Book getBook(String name) {
        return books.get(name);
    }

    public Cache<Book> getBooks() {
        return books;
    }

    public Cache<Book.Page> getAllPages() {
        return pages;
    }
}
