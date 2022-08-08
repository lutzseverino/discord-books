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

public class JDAReceivable implements Receivable {
    private final MessageChannel channel;

    public JDAReceivable(MessageChannel channel) {
        this.channel = channel;
    }

    public JDAReceivable(@NotNull Guild guild, String channelId) {
        this(guild.getTextChannelById(channelId));
    }

    @Override public void receive(@NotNull Sendable sendable) {
        channel.sendMessage(JDAMessage.buildMessage(sendable)).queue();
    }

    @Override public void receive(Book book) {
        receive(book, 0);
    }

    @Override public void receive(@NotNull Book book, int index) {
        MessageAction action = channel.sendMessage(JDAMessage.buildMessage(book.getPage(index)));

        if (book.getId().isEmpty())
            action.queue(message -> DiscordBooks.getTemporaryDatabase().set(message.getId(), book));
        else action.queue();
    }

}
