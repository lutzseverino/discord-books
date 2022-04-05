package st.networkers.discordbooks.jda.send;

import net.dv8tion.jda.api.entities.MessageEmbed;
import st.networkers.discordbooks.send.SendableEmbed;

public class JDAMessageEmbed implements SendableEmbed {
    private final MessageEmbed message;

    public JDAMessageEmbed(MessageEmbed message) {
        this.message = message;
    }

    @Override public MessageEmbed getObject() {
        return message;
    }

    @Override public String getId() {
        return message.toString();
    }
}
