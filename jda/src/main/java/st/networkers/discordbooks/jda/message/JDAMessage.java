package st.networkers.discordbooks.jda.message;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.internal.entities.DataMessage;
import st.networkers.discordbooks.message.Embed;
import st.networkers.discordbooks.message.Sendable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDAMessage implements Sendable {
    private final Message message;
    private final List<Embed> embeds = new ArrayList<>();

    public JDAMessage(Message message) {
        this.message = message;
    }

    @Override
    public Optional<String> getText() {
        return message.getContentRaw().isEmpty() ? Optional.empty() : Optional.of(message.getContentRaw());
    }

    @Override public Sendable setEmbeds(Embed... embeds) {
        this.embeds.addAll(List.of(embeds));
        return this;
    }

    @Override public List<Embed> getEmbeds() {
        return this.embeds;
    }
}
