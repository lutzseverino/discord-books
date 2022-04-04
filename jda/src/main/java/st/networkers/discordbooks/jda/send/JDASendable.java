package st.networkers.discordbooks.jda.send;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import st.networkers.discordbooks.send.Sendable;

public class JDASendable implements Sendable {
    private final Message message;
    private final MessageEmbed messageEmbed;
    private final MessageChannel channel;
    private MessageAction action;

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
        if (message != null)
            action = channel.sendMessage(message);
        else if (messageEmbed != null)
            action = channel.sendMessageEmbeds(messageEmbed);

        action.queue();
    }
}
