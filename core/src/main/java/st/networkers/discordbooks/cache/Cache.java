package st.networkers.discordbooks.cache;

public interface Cache<T> {
    void add(T t);

    // TODO: Abstract to be usable by other classes
    T get(String name);

    Iterable<T> getAll();

    void remove(String name);

    void remove(T t);
}
