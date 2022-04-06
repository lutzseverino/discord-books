package st.networkers.discordbooks.jda.message;

import net.dv8tion.jda.api.entities.MessageEmbed;
import st.networkers.discordbooks.message.Sendable;

public class JDAMessageEmbed implements Sendable {
    private final MessageEmbed[] embeds;

    public JDAMessageEmbed(MessageEmbed... message) {
        this.embeds = message;
    }

    @Override public MessageEmbed[] getMessage() {
        return embeds;
    }

    @Override public String getId() {
        return embeds.toString();
    }
}
