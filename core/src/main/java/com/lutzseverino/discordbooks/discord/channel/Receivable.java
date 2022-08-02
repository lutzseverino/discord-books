package com.lutzseverino.discordbooks.discord.channel;

import com.lutzseverino.discordbooks.discord.message.Sendable;

public interface Receivable {
    Receivable receive(Sendable message);

}
