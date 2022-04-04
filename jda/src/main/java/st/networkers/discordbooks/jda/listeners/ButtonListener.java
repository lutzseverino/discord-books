package st.networkers.discordbooks.jda.listeners;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import st.networkers.discordbooks.book.Book;
import st.networkers.discordbooks.cache.Cache;
import st.networkers.discordbooks.jda.JDABooks;
import st.networkers.discordbooks.jda.book.JDABook;

public class ButtonListener extends ListenerAdapter {
    private final Cache<? extends Book> books;

    public ButtonListener(Cache<? extends Book> books) {
        this.books = books;
    }

    @Override public void onButtonInteraction(ButtonInteractionEvent event) {
        // TODO: Implement button listener
    }
}
