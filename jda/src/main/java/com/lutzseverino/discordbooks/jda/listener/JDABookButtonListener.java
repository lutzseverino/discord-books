package com.lutzseverino.discordbooks.jda.listener;

import com.lutzseverino.discordbooks.book.Book;
import com.lutzseverino.discordbooks.cache.Cache;
import com.lutzseverino.discordbooks.discord.message.Sendable;
import com.lutzseverino.discordbooks.jda.discord.message.JDAMessage;
import com.lutzseverino.discordbooks.jda.errors.JDABookErrorHandler;
import com.lutzseverino.discordbooks.jda.errors.JDABookErrorHandlerImpl;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

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

            // ID must not be null, we're preventing this event from
            // firing if the button is of type LINK.
            assert id != null;

            // Checks for at least: example@pageNum@ownerId
            // Extra owner IDs seperated by # are not checked, as they might
            // not be there.
            if (!id.matches("[a-zA-Z]+@\\d+@\\d+")) return;

            String[] split = id.split("@");
            String bookID = split[0];
            int pageToSet = Integer.parseInt(split[1]);
            String[] ownerIDs = split[2].split("#");
            Book book = books.get(bookID);

            if (book != null) {
                if (!ownerIDs[0].equals("0") && Arrays.stream(ownerIDs).noneMatch(s -> event.getUser().getId().equals(s))) {
                    errorHandler.whenUserIsNotOwner(event);
                    return;
                }

                Sendable page = book.edit(pageToSet, ownerIDs);
                event.editMessage(JDAMessage.buildMessage(page)).queue();

            } else errorHandler.whenBookIsNull(event);
        }
    }
}
