package st.networkers.discordBooks.book;

public class Page<T> {
    private final T page;

    public Page(T page) {
        this.page = page;
    }

    public T getPage() {
        return page;
    }
}
