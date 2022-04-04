package st.networkers.discordbooks.jda.listeners;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import st.networkers.discordbooks.cache.BookCache;

public class ButtonListener extends ListenerAdapter {
    private final BookCache books;

    public ButtonListener(BookCache books) {
        this.books = books;
    }

    @Override public void onButtonInteraction(ButtonInteractionEvent event) {
        // TODO: Implement button listener
    }
}
