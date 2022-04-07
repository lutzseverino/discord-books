package st.networkers.discordbooks.book;

import org.jetbrains.annotations.NotNull;
import st.networkers.discordbooks.message.Sendable;

import java.util.ArrayList;
import java.util.List;

public abstract class Book {
    protected final ArrayList<Page> pages = new ArrayList<>();
    protected String name;

    public Book(String name) {
        this.name = name;
    }

    /**
     * @param pages the pages to add
     */
    public void addPages(Page... pages) {
        this.pages.addAll(List.of(pages));
    }

    /**
     * @param pages the pages to add
     */
    public void addPages(Sendable<?> @NotNull ... pages) {
        for (Sendable<?> page : pages)
            this.pages.add(new Page(page));
    }

    /**
     * @return a list of pages
     */
    public List<Page> getPages() {
        return pages;
    }

    /**
     * Uses the provided page as the index, then adds
     * one and returns the result.
     *
     * @param page the page to use as an index
     * @return the next page, null if the final index is -1
     */
    public Page getNextPage(Page page) {
        int index = pages.indexOf(page);

        return index == -1 ? null : pages.get(index + 1);
    }

    /**
     * Uses the provided page as the index, then subtracts
     * one and returns the result.
     *
     * @param page the page to use as an index
     * @return the previous page, null if the final index is -1
     */
    public Page getPreviousPage(Page page) {
        int index = pages.indexOf(page);

        return index == -1 ? null : pages.get(index - 1);
    }

    /**
     * @return the name of the book
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name of the book
     */
    public void setName(String name) {
        this.name = name;
    }

    public class Page {
        private final Sendable<?> content;
        private final Book book = Book.this;
        /**
         * @param content the content of the page as a {@link Sendable}
         */
        public Page(@NotNull Sendable<?> content) {
            this.content = content;}

        /**
         * @return the content of the page
         */
        public Sendable<?> getContent() {
            return content;
        }

        /**
         * @return the book this page is in
         */
        public Book getBook() {
            return book;
        }
    }
}
