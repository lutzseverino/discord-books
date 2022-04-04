package st.networkers.discordbooks.book;

public class Page<T> {
    private final T content;

    public Page(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }
}
