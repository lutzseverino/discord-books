package com.lutzseverino.discordbooks.jda.discord.channel;

import com.lutzseverino.discordbooks.DiscordBooks;
import com.lutzseverino.discordbooks.book.Book;
import com.lutzseverino.discordbooks.discord.channel.Receivable;
import com.lutzseverino.discordbooks.discord.message.Sendable;
import com.lutzseverino.discordbooks.jda.discord.message.JDAMessage;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import org.jetbrains.annotations.NotNull;

import javax.annotation.CheckReturnValue;

public class JDAReceivable implements Receivable {
    private final MessageChannel channel;

    public JDAReceivable(MessageChannel channel) {
        this.channel = channel;
    }

    public JDAReceivable(@NotNull Guild guild, String channelId) {
        this(guild.getTextChannelById(channelId));
    }

    /**
     * Builds a JDA message and sends it.
     *
     * @param sendable the {@link Sendable} to send
     */
    @Override public void receive(@NotNull Sendable sendable) {
        channel.sendMessage(JDAMessage.buildMessage(sendable)).queue();
    }

    /**
     * If the book is permanent, the first page will be sent and
     * queued, otherwise, it will additionally be set in the
     * temporary database.
     *
     * @param book the {@link Book} to send
     */
    @Override public void receive(@NotNull Book book) {
        receive(book, 0);
    }

    /**
     * If the book is permanent, the page at the provided index
     * will be sent and queued, otherwise, it will additionally
     * be set in the temporary database.
     *
     * @param book  the {@link Book} to send
     * @param index the page index
     */
    @Override public void receive(@NotNull Book book, int index) {
        if (!book.getId().isEmpty()) receivePerm(book, index);
        else receiveTemp(book, index);
    }

    /**
     * Sends the book's page at the provided index and queues it.
     *
     * @param book  the {@link Book} to send
     * @param index the page index
     */
    @Override public void receivePerm(@NotNull Book book, int index) {
        receiveAction(book, index).queue();
    }

    /**
     * Sends the book's page at the provided index, sets it to
     * the temporary database and queues it.
     *
     * @param book  the {@link Book} to send
     * @param index the page index
     */
    @Override public void receiveTemp(@NotNull Book book, int index) {
        channel.sendMessage(JDAMessage.buildMessage(book.getPage(index)))
                .queue(message -> DiscordBooks.getTemporaryDatabase().set(message.getId(), book));
    }

    /**
     * Sends the book's page at the provided index.
     *
     * @param book  the {@link Book} to send
     * @param index the page index
     * @return the created {@link MessageAction}
     */
    @CheckReturnValue
    public MessageAction receiveAction(@NotNull Book book, int index) {
        return channel.sendMessage(JDAMessage.buildMessage(book.getPage(index)));
    }
}
