package st.networkers.discordbooks.discord.channel;

import st.networkers.discordbooks.discord.message.Sendable;

public interface Receivable {
    Receivable receive(Sendable message);

}
