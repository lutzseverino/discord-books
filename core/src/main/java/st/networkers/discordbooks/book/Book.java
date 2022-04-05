package st.networkers.discordbooks.book;

import st.networkers.discordbooks.send.SendableMessage;

import java.util.ArrayList;
import java.util.List;

public abstract class Book {
    protected String name;
    protected final ArrayList<Page> pages = new ArrayList<>();

    /**
     * @param pages the pages to add
     */
    public void addPages(Page... pages) {
        this.pages.addAll(List.of(pages));
    }

    /**
     * @param content the pages' content to add
     */
    public final void addPages(SendableMessage... content) {
        for (SendableMessage page : content)
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

    /**
     * @param name the name of the book
     */
    public void setName(String name) {
        this.name = name;
    }

    public class Page {
        private final SendableMessage content;
        private final Book book = Book.this;
        private final String id;

        public Page(SendableMessage content) {
            this.content = content;
            this.id = content.getObject().toString();
        }

        /**
         * @return the content of the page
         */
        public SendableMessage getContent() {
            return content;
        }

        /**
         * @return the book this page is in
         */
        public Book getBook() {
            return book;
        }

        public String getId() {
            return id;
        }
    }
}
