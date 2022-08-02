package com.lutzseverino.discordbooks.jda.discord.channel;

import com.lutzseverino.discordbooks.discord.channel.Receivable;
import com.lutzseverino.discordbooks.discord.message.Sendable;
import com.lutzseverino.discordbooks.jda.discord.message.JDAMessage;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageChannel;
import org.jetbrains.annotations.NotNull;

public class JDAReceivable implements Receivable {
    private final MessageChannel channel;

    public JDAReceivable(MessageChannel channel) {
        this.channel = channel;
    }

    public JDAReceivable(Guild guild, String channelId) {
        this(guild.getTextChannelById(channelId));
    }

    @Override public JDAReceivable receive(@NotNull Sendable sendable) {
        channel.sendMessage(JDAMessage.buildMessage(sendable)).queue();
        return this;
    }
}
