package st.networkers.discordbooks.jda.book;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import st.networkers.discordbooks.book.Book;
import st.networkers.discordbooks.jda.message.JDAMessage;
import st.networkers.discordbooks.jda.message.JDAMessageEmbed;
import st.networkers.discordbooks.message.Sendable;

import java.util.Arrays;

public class JDABook extends Book {
    private Button backButton;
    private Button nextButton;

    /**
     * Sends the first book page to a specified
     * channel.
     *
     * @param channel the Discord message channel to send the book to
     */
    public void send(MessageChannel channel) {
        send(channel, 0);
    }

    /**
     * Sends the specified book page to the provided
     * channel.
     *
     * @param channel the Discord message channel to send the book to
     * @param index   the page number to send
     */
    public void send(MessageChannel channel, int index) {
        Sendable sendable = pages.get(index).getContent();

        if (sendable instanceof JDAMessage) {
            JDAMessage jdaMessage = (JDAMessage) sendable;
            applyActionRow(channel.sendMessage(jdaMessage.getMessage()), index);
        } else if (sendable instanceof JDAMessageEmbed) {
            JDAMessageEmbed jdaMessageEmbed = (JDAMessageEmbed) sendable;
            applyActionRow(channel.sendMessageEmbeds(Arrays.asList(jdaMessageEmbed.getMessage())), index);
        } else
            throw new IllegalArgumentException("Page sendable at index " + index + " of book + \"" + this.getName() + "\" is neither a JDAMessage or JDAMessageEmbed");
    }

    /**
     * @param component the button to set as the back button
     */
    public void setBackButton(Button component) {
        this.backButton = component;
    }

    /**
     * @param component the button to set as the next button
     */
    public void setNextButton(Button component) {
        this.nextButton = component;
    }

    /**
     * @param action the message action to apply the buttons to
     * @param index  the index of the page
     */
    private void applyActionRow(MessageAction action, int index) {
        action.setActionRow(backButton.withDisabled(index == 0), nextButton.withDisabled(pages.size() == index)).queue();
    }
}