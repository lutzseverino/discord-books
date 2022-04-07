package st.networkers.discordbooks.jda.book;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import net.dv8tion.jda.internal.interactions.component.ButtonImpl;
import org.jetbrains.annotations.NotNull;
import st.networkers.discordbooks.book.Book;
import st.networkers.discordbooks.jda.message.JDAMessage;
import st.networkers.discordbooks.jda.message.JDAMessageEmbed;
import st.networkers.discordbooks.message.Sendable;

import javax.annotation.CheckReturnValue;
import java.util.ArrayList;
import java.util.List;

public class JDABook extends Book {
    private Button previousButton = new ButtonImpl(null, "Previous", ButtonStyle.PRIMARY, true, null);
    private Button nextButton = new ButtonImpl(null, "Next", ButtonStyle.PRIMARY, true, null);
    private final List<Button> buttons = new ArrayList<>();
    private final List<ActionRow> actionRows = new ArrayList<>();

    public JDABook(String name) {
        super(name);
    }

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
        Sendable<?> sendable = pages.get(index).getContent();

        if (sendable instanceof JDAMessage)
            applyActionRows(channel.sendMessage(((JDAMessage) sendable).getMessage()), index).queue();
        else if (sendable instanceof JDAMessageEmbed)
            applyActionRows(channel.sendMessageEmbeds(List.of(((JDAMessageEmbed) sendable).getMessage())), index).queue();
        else
            throw new IllegalArgumentException("Sendable of Book page must be a JDAMessage or JDAMessageEmbed");
    }

    /**
     * Sets the previous button action to the book.
     *
     * @param style the style of the button
     * @param label the label of the button
     * @throws IllegalArgumentException if the style is not a button style other than LINK
     */
    public void setPreviousButton(ButtonStyle style, String label) {
        if (style != ButtonStyle.LINK)
            this.previousButton = new ButtonImpl(null, label, style, true, null);
        else throw new IllegalArgumentException("Back button style must be a button style other than LINK");
    }

    /**
     * Sets the next button action to the book.
     *
     * @param style the style of the button
     * @param label the label of the button
     * @throws IllegalArgumentException if the style is not a button style other than LINK
     */
    public void setNextButton(ButtonStyle style, String label) {
        if (style != ButtonStyle.LINK)
            this.nextButton = new ButtonImpl(null, label, style, false, null);
        else throw new IllegalArgumentException("Next button style must be a button style other than LINK");
    }

    /**
     * Adds extra desired buttons to the book.
     * The actions of these will need to be handled
     * by the user.
     *
     * @param buttons the buttons to add
     */
    public void addButtons(Button... buttons) {
        this.buttons.addAll(List.of(buttons));
    }

    /**
     * Adds additional action rows to the book.
     *
     * @param actionRows the action rows to add
     */
    public void addActionRows(ActionRow... actionRows) {
        this.actionRows.addAll(List.of(actionRows));
    }

    /**
     * Applies all the final action rows to the message.
     *
     * @param action the message action to apply the buttons to
     * @param index  the index of the page
     */
    @CheckReturnValue
    private @NotNull MessageAction applyActionRows(@NotNull MessageAction action, int index) {
        buttons.add(0, previousButton.withDisabled(index == 0).withId(getName() + "-" + --index));
        buttons.add(1, nextButton.withDisabled(index == pages.size() - 1).withId(getName() + "-" + ++index));
        ActionRow bookActionRow = ActionRow.of(buttons);
        actionRows.add(0, bookActionRow);

        return action.setActionRows(actionRows);
    }
}