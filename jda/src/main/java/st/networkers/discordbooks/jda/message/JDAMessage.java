package st.networkers.discordbooks.jda.message;

import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import st.networkers.discordbooks.message.Sendable;

public class JDAMessage implements Sendable<Message> {
    private final Message message;

    public JDAMessage(Message message) {
        this.message = message;
    }

    public JDAMessage(String message) {
        this.message = new MessageBuilder(message).build();
    }

    @Override public Message getMessage() {
        return message;
    }}
