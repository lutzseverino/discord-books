package st.networkers.discordbooks.jda.send;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;
import st.networkers.discordbooks.send.Sendable;

public class JDASendable implements Sendable {
    private final Message message;
    private final MessageEmbed messageEmbed;
    private final MessageChannel channel;
    private Object sendableObject;


    private JDASendable(Message message, MessageEmbed messageEmbed, MessageChannel channel) {
        this.message = message;
        this.messageEmbed = messageEmbed;
        this.channel = channel;
    }

    public JDASendable(Message message, MessageChannel channel) {
        this(message, null, channel);
    }

    public JDASendable(MessageEmbed messageEmbed, MessageChannel channel) {
        this(null, messageEmbed, channel);
    }

    @Override public void send() {
        if (message != null) {
            channel.sendMessage(message).queue();
            sendableObject = message;
        } else if (messageEmbed != null) {
            channel.sendMessageEmbeds(messageEmbed).queue();
            sendableObject = messageEmbed;
        }
    }

    @Override public Object get() {
        return sendableObject;
    }
}
