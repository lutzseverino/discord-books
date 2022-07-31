package st.networkers.discordbooks.channel;

import st.networkers.discordbooks.message.Sendable;

public interface Receivable {
    Receivable receive(Sendable message);

}
