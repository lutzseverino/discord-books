package com.lutzseverino.discordbooks.jda.listener;

import com.lutzseverino.discordbooks.DiscordBooks;
import com.lutzseverino.discordbooks.book.Book;
import com.lutzseverino.discordbooks.jda.discord.message.JDAMessage;
import com.lutzseverino.discordbooks.jda.errors.JDABookErrorHandler;
import com.lutzseverino.discordbooks.jda.errors.JDABookErrorHandlerImpl;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public class JDABookButtonListener extends ListenerAdapter {
    private final Map<String, Book> books;
    private final JDABookErrorHandler errorHandler;

    public JDABookButtonListener(Map<String, Book> books, JDABookErrorHandler errorHandler) {
        this.books = books;
        this.errorHandler = errorHandler;
    }

    public JDABookButtonListener(Map<String, Book> books) {
        this(books, new JDABookErrorHandlerImpl());
    }

    @Override public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        if (event.getButton().getStyle() != ButtonStyle.LINK) {
            String id = event.getButton().getId();

            // ID cannot be null, we're preventing this event from
            // firing if the button is of type LINK.
            assert id != null;

            if (!id.startsWith("book:")) return;
            id = id.substring("book:".length());

            String[] split = id.split("@");
            String bookID = split[0];
            int pageToSet = Integer.parseInt(split[1]);

            // We search for source books, if none, search the cache for temporary ones.
            Book book = books.getOrDefault(bookID, DiscordBooks.getCachedBook(event.getMessageId()));

            if (book != null) {
                List<String> owners = book.getOwners();

                if (!owners.isEmpty() && owners.stream().noneMatch(s -> event.getUser().getId().equals(s))) {
                    errorHandler.whenUserIsNotOwner(event);
                    return;
                }

                event.editMessage(JDAMessage.buildMessage(book.build(pageToSet))).queue();

            } else errorHandler.whenBookIsNull(event);
        }
    }
}
