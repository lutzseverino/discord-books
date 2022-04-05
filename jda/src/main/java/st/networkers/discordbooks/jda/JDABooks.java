package st.networkers.discordbooks.jda;

import net.dv8tion.jda.api.JDA;
import st.networkers.discordbooks.DiscordBooks;
import st.networkers.discordbooks.jda.listeners.ButtonListener;

public class JDABooks extends DiscordBooks {

    public JDABooks(JDA jdaInstance) {
        jdaInstance.addEventListener(new ButtonListener(getBooks(), getAllPages()));
    }
}
