package st.networkers.discordbooks;

import st.networkers.discordbooks.book.Book;
import st.networkers.discordbooks.cache.Cache;
import st.networkers.discordbooks.cache.BookCache;

public class DiscordBooks {
    protected final Cache<Book> books = new BookCache();

    public void addBooks(Book... books) {
        for (Book book : books)
            this.books.add(book);
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
}
