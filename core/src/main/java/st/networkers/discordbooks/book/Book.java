package st.networkers.discordbooks.book;

import st.networkers.discordbooks.send.Sendable;

import java.util.ArrayList;
import java.util.List;

public abstract class Book {
    protected final String name;
    protected final ArrayList<Page> pages = new ArrayList<>();

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
     * @param content the pages' content to add
     */
    public final void addPages(Sendable... content) {
        for (Sendable page : content)
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
     * @return the book's name
     */
    public String getName() {
        return name;
    }

    public static class Page {
        Sendable content;

        public Page(Sendable content) {
            this.content = content;
        }

        /**
         * @return the content of the page
         */
        public Sendable getContent() {
            return content;
        }
    }
}
