package st.networkers.discordbooks.book;

import java.util.ArrayList;
import java.util.List;

public abstract class Book {
    private final String name;
    private final ArrayList<Page<?>> pages = new ArrayList<>();

    public Book(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addPages(Page<?>... pages) {
        this.pages.addAll(List.of(pages));
    }

    @SafeVarargs public final <T> void addPages(T... pages) {
        for (T page : pages)
            this.pages.add(new Page<>(page));
    }

    List<Page<?>> getPages() {
        return pages;
    }

    Page<?> getNextPage(Page<?> page) {
        int index = pages.indexOf(page);

        return index == -1 ? null : pages.get(index + 1);
    }

    Page<?> getPreviousPage(Page<?> page) {
        int index = pages.indexOf(page);

        return index == -1 ? null : pages.get(index - 1);
    }
}
