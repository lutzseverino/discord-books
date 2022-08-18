package com.lutzseverino.discordbooks.jda.listener;

import com.lutzseverino.discordbooks.DiscordBooks;
import com.lutzseverino.discordbooks.book.Book;
import com.lutzseverino.discordbooks.database.BookDB;
import com.lutzseverino.discordbooks.jda.discord.message.JDAMessage;
import com.lutzseverino.discordbooks.jda.errors.JDABookErrorHandler;
import com.lutzseverino.discordbooks.jda.errors.JDABookErrorHandlerImpl;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class JDABookButtonListener extends ListenerAdapter {
    private final JDABookErrorHandler errorHandler;

    public JDABookButtonListener(JDABookErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    public JDABookButtonListener() {
        this(new JDABookErrorHandlerImpl());
    }

    @Override public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        String buttonId = event.getComponentId();

        if (!buttonId.startsWith("book:")) return;

        buttonId = buttonId.substring("book:".length());

        String[] split = buttonId.split("@");
        String bookId = split[0];
        boolean temporary = bookId.isEmpty();
        int index = Integer.parseInt(split[1]);

        bookId = temporary ? event.getMessageId() : bookId;

        BookDB database = temporary ? DiscordBooks.getTemporaryDatabase() : DiscordBooks.getDatabase();
        Book book = database.get(bookId);

        if (book != null) {
            List<String> owners = book.getOwners();

            if (owners.isEmpty() || owners.stream().anyMatch(s -> event.getUser().getId().equals(s))) {
                event.editMessage(JDAMessage.buildMessage(book.getPage(index))).queue();
                // If the book is temporary, the updated book will be saved.
                if (temporary) database.set(event.getMessageId(), book);
            } else errorHandler.whenUserIsNotOwner(event);
        } else errorHandler.whenBookIsNull(event);

    }
}
