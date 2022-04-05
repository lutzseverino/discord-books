package st.networkers.discordbooks.jda.send;

import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import st.networkers.discordbooks.send.SendableMessage;

public class JDAMessage implements SendableMessage {
    private final Message message;

    public JDAMessage(Message message) {
        this.message = message;
    }

    public JDAMessage(String message) {
        this.message = new MessageBuilder(message).build();
    }

    @Override public Message getObject() {
        return message;
    }

    @Override public String getId() {
        return message.toString();
    }
}
