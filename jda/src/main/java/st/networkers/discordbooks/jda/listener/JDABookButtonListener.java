package st.networkers.discordbooks.jda.listener;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Message.Interaction;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import org.jetbrains.annotations.NotNull;
import st.networkers.discordbooks.book.Book;
import st.networkers.discordbooks.cache.Cache;
import st.networkers.discordbooks.jda.book.JDABook;
import st.networkers.discordbooks.jda.errors.JDABookErrorHandler;
import st.networkers.discordbooks.jda.errors.JDABookErrorHandlerImpl;

public class JDABookButtonListener extends ListenerAdapter {
    private final Cache<? extends Book> books;
    private final JDABookErrorHandler errorHandler;

    public JDABookButtonListener(Cache<? extends Book> books, JDABookErrorHandler errorHandler) {
        this.books = books;
        this.errorHandler = errorHandler;
    }

    public JDABookButtonListener(Cache<? extends Book> books) {
        this(books, new JDABookErrorHandlerImpl());
    }

    @Override public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        if (event.getButton().getStyle() != ButtonStyle.LINK) {
            String id = event.getButton().getId();

            //noinspection ConstantConditions
            String[] split = id.split("@");
            String bookID = split[0];
            int pageToSet = Integer.parseInt(split[1]);
            String ownerID = split[2];
            JDABook book = (JDABook) books.get(bookID);

            Message message = event.getMessage();
            Interaction interaction = message.getInteraction();

            if (book != null) {
                if (((interaction != null && !interaction.getUser().equals(event.getUser())) || (!event.getUser().getId().equals(ownerID))) && !book.isNavigationPublic()) {
                    errorHandler.whenUserIsNotOwner(event);
                    return;
                }

                book.edit(event.deferEdit(), pageToSet, ownerID);
            } else errorHandler.whenBookIsNull(event);
        }
    }
}
