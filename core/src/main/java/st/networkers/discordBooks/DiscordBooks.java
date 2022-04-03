package st.networkers.discordBooks;

import st.networkers.discordBooks.book.Book;
import st.networkers.discordBooks.cache.BookCache;
import st.networkers.discordBooks.cache.BookCacheImpl;

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
