package st.networkers.discordbooks.jda;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.MessageChannel;
import org.jetbrains.annotations.NotNull;
import st.networkers.discordbooks.DiscordBooks;
import st.networkers.discordbooks.book.Book;
import st.networkers.discordbooks.jda.book.JDABook;
import st.networkers.discordbooks.jda.listener.ButtonListener;

public class JDABooks extends DiscordBooks {

    public JDABooks(@NotNull JDA jdaInstance) {
        jdaInstance.addEventListener(new ButtonListener(getBooks()));
    }

    public JDABooks() {
    }

    /**
     * @param name    the name of the book
     * @param channel the channel to send the book to
     */
    public void sendBook(String name, MessageChannel channel) {
        sendBook(name, channel, 0);
    }

    /**
     * @param name    the name of the book
     * @param channel the channel to send the book to
     * @param index   the index of the page to send
     * @throws IllegalArgumentException if the Book is not a JDABook
     */
    public void sendBook(String name, MessageChannel channel, int index) {
        Book book = getBooks().get(name);

        if (book instanceof JDABook)
            ((JDABook) book).send(channel, index);
        else throw new IllegalArgumentException("Book is not a JDABook");
    }
}
