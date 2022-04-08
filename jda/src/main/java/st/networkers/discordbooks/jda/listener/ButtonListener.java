package st.networkers.discordbooks.jda.listener;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import org.jetbrains.annotations.NotNull;
import st.networkers.discordbooks.book.Book;
import st.networkers.discordbooks.cache.Cache;
import st.networkers.discordbooks.jda.book.JDABook;
import st.networkers.discordbooks.jda.errors.JDABookErrorHandler;

public class ButtonListener extends ListenerAdapter {
    private final Cache<? extends Book> books;
    private final JDABookErrorHandler errorHandler;

    public ButtonListener(Cache<? extends Book> books, JDABookErrorHandler errorHandler) {
        this.books = books;
        this.errorHandler = errorHandler;
    }

    @Override public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        if (event.getButton().getStyle() != ButtonStyle.LINK) {
            String id = event.getButton().getId();

            // Button ID can't be null because of the ButtonStyle.LINK check
            assert id != null;

            String[] split = id.split("-");
            String bookId = split[0];
            int pageToSet = Integer.parseInt(split[1]);
            JDABook book = (JDABook) books.get(bookId);

            Message message = event.getMessage();
            Message.Interaction interaction = message.getInteraction();


            if (book != null) {
                if (interaction != null && interaction.getUser() == event.getUser() && !book.isNavigationPublic()) {
                    errorHandler.whenBookIsNull(event);
                    return;
                }

                book.edit(event.deferEdit(), pageToSet);
            } else errorHandler.whenBookIsNull(event);
        }
    }
}
