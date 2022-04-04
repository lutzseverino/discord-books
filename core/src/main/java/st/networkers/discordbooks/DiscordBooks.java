package st.networkers.discordbooks;

import st.networkers.discordbooks.book.Book;
import st.networkers.discordbooks.cache.BookCache;
import st.networkers.discordbooks.cache.BookCacheImpl;

public class DiscordBooks {
    protected final BookCache books = new BookCacheImpl();

    public void addBooks(Book... books) {
        for (Book book : books) {
            this.books.add(book.getName(), book);
        }
    }

    public Book getBook(String name) {
        return books.get(name);
    }

    public BookCache getBooks() {
        return books;
    }
}
