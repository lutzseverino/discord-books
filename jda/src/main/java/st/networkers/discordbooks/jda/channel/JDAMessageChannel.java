package st.networkers.discordbooks.jda.channel;

import net.dv8tion.jda.api.entities.MessageChannel;
import org.jetbrains.annotations.NotNull;
import st.networkers.discordbooks.channel.Receivable;
import st.networkers.discordbooks.jda.message.JDAEmbed;
import st.networkers.discordbooks.message.Sendable;

public class JDAMessageChannel implements Receivable {
    private final MessageChannel channel;

    public JDAMessageChannel(MessageChannel channel) {
        this.channel = channel;
    }

    @Override public JDAMessageChannel receive(@NotNull Sendable sendable) {
        sendable.getText().ifPresentOrElse(text -> {
            if (!sendable.getEmbeds().isEmpty())
                channel.sendMessage(text).setEmbeds(JDAEmbed.toMessageEmbeds(sendable.getEmbeds())).queue();
        }, () -> {
            if (!sendable.getEmbeds().isEmpty())
                channel.sendMessageEmbeds(JDAEmbed.toMessageEmbeds(sendable.getEmbeds())).queue();
            else throw new IllegalArgumentException("A Sendable must have text and/or embeds");
        });

        return this;
    }
}
