package st.networkers.discordbooks.jda.book;

import net.dv8tion.jda.api.entities.MessageChannel;
import st.networkers.discordbooks.book.Book;

public class JDABook extends Book {

    public JDABook(String name) {
        super(name);
    }

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
        pages.get(index).getContent().send();
    }
}