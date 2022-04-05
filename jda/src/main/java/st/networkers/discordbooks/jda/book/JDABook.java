package st.networkers.discordbooks.jda.book;

import net.dv8tion.jda.api.entities.MessageChannel;
import st.networkers.discordbooks.book.Book;
import st.networkers.discordbooks.jda.send.JDAMessage;
import st.networkers.discordbooks.jda.send.JDAMessageEmbed;
import st.networkers.discordbooks.send.Sendable;

public class JDABook extends Book {

    /**
     * Sends the first book page to a specified
     * channel.
     *
     * @param channel the Discord message channel to send the book to
     */
    public void sendBook(MessageChannel channel) {
        sendBook(channel, 0);
    }

    /**
     * Sends the specified book page to the provided
     * channel.
     *
     * @param channel the Discord message channel to send the book to
     * @param index   the page number to send
     */
    public void sendBook(MessageChannel channel, int index) {
        Sendable sendable = pages.get(index).getContent();

        if (sendable instanceof JDAMessage) {
            JDAMessage jdaMessage = (JDAMessage) sendable;
            channel.sendMessage(jdaMessage.getObject()).queue();
        } else if (sendable instanceof JDAMessageEmbed) {
            JDAMessageEmbed jdaMessageEmbed = (JDAMessageEmbed) sendable;
            channel.sendMessageEmbeds(jdaMessageEmbed.getObject()).queue();
        } else {
            throw new IllegalArgumentException("Page sendable is neither a JDAMessage or JDAMessageEmbed");
        }
    }
}