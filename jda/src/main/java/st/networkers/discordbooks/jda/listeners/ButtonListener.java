package st.networkers.discordbooks.jda.listeners;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import st.networkers.discordbooks.book.Book;
import st.networkers.discordbooks.cache.Cache;

public class ButtonListener extends ListenerAdapter {
    private final Cache<? extends Book> books;
    private final Cache<Book.Page> pages;

    public ButtonListener(Cache<? extends Book> books, Cache<Book.Page> pages) {
        this.books = books;
        this.pages = pages;
    }

    @Override public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {

    }
}
