package com.lutzseverino.discordbooks.discord.channel;

import com.lutzseverino.discordbooks.book.Book;
import com.lutzseverino.discordbooks.discord.message.Sendable;
import org.jetbrains.annotations.NotNull;

public interface Receivable {
    void receive(Sendable message);

    void receive(Book book);

    void receive(@NotNull Book book, int index);
}
