package st.networkers.discordBooks.jda;

import net.dv8tion.jda.api.JDA;
import st.networkers.discordBooks.DiscordBooks;
import st.networkers.discordBooks.jda.listeners.ButtonListener;

public class JDABooks extends DiscordBooks {

    public JDABooks(JDA jdaInstance) {
        jdaInstance.addEventListener(new ButtonListener(getBooks()));
    }
}
