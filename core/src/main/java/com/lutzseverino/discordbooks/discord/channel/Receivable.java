package com.lutzseverino.discordbooks.discord.channel;

import com.lutzseverino.discordbooks.book.Book;
import com.lutzseverino.discordbooks.discord.message.Sendable;
import org.jetbrains.annotations.NotNull;

public interface Receivable {
    void receive(@NotNull Sendable message);

    void receive(@NotNull Book book);

    void receive(@NotNull Book book, int index);

    void receivePerm(@NotNull Book book, int index);

    void receiveTemp(@NotNull Book book, int index);
}
