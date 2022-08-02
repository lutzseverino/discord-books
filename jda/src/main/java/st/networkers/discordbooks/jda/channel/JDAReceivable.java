package st.networkers.discordbooks.jda.channel;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageChannel;
import org.jetbrains.annotations.NotNull;
import st.networkers.discordbooks.discord.channel.Receivable;
import st.networkers.discordbooks.discord.message.Sendable;
import st.networkers.discordbooks.jda.message.JDAMessage;

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
