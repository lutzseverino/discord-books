package st.networkers.discordbooks.cache;

public interface Cache<T> {
    void add(T t);

    T get(String id);

    Iterable<T> getAll();

    void remove(String name);

    void remove(T t);
}
