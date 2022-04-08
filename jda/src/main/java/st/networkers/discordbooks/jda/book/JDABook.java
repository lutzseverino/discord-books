package st.networkers.discordbooks.jda.book;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import net.dv8tion.jda.api.requests.restaction.interactions.MessageEditCallbackAction;
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
     * @throws IndexOutOfBoundsException if the index is less than 0 or greater than the number of pages
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
     * Uses an edit action to edit the current page to
     * the specified index.
     *
     * @param action the action to edit
     * @param index  the index of the page to edit to
     */
    public void edit(MessageEditCallbackAction action, int index) {
        Sendable<?> sendable = pages.get(index).getContent();

        if (sendable instanceof JDAMessage)
            applyActionRows(action.setContent(((JDAMessage) sendable).getMessage().getContentRaw()), index).queue();
        else if (sendable instanceof JDAMessageEmbed)
            applyActionRows(action.setEmbeds(List.of(((JDAMessageEmbed) sendable).getMessage())), index).queue();
        else
            throw new IllegalArgumentException("Sendable of Book page must be a JDAMessage or JDAMessageEmbed");
    }

    /**
     * Gets button that will be used to display
     * the previous page.
     *
     * @param style the style of the button
     * @param label the label of the button
     * @throws IllegalArgumentException if the style is not a button style other than LINK
     */
    public Button getPreviousButton(ButtonStyle style, String label) {
        if (style != ButtonStyle.LINK)
            previousButton = previousButton.withStyle(style).withLabel(label);
        else throw new IllegalArgumentException("Back button style must be a button style other than LINK");

        return previousButton;
    }

    /**
     * Gets button that will be used to display
     * the next page.
     *
     * @param style the style of the button
     * @param label the label of the button
     * @throws IllegalArgumentException if the style is not a button style other than LINK
     */
    public Button getNextButton(ButtonStyle style, String label) {
        if (style != ButtonStyle.LINK)
            nextButton = nextButton.withStyle(style).withLabel(label);
        else throw new IllegalArgumentException("Next button style must be a button style other than LINK");

        return nextButton;
    }

    /**
     * Adds buttons to the book.
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
     * This method can only be used on send.
     *
     * @param action the message action to apply the buttons to
     * @param index  the index of the current page
     */
    @CheckReturnValue
    private @NotNull MessageAction applyActionRows(@NotNull MessageAction action, int index) {
        setActionRowsUp(index);
        return action.setActionRows(actionRows);
    }

    /**
     * Applies all the final action rows to the message.
     * This method can only be used on edit.
     *
     * @param action the message action to apply the buttons to
     * @param index  the index of the current page
     */
    @CheckReturnValue
    private @NotNull MessageEditCallbackAction applyActionRows(@NotNull MessageEditCallbackAction action, int index) {
        setActionRowsUp(index);
        return action.setActionRows(actionRows);
    }

    /**
     * Adds the label and disabled state of the buttons
     * and all additional buttons and action rows.
     *
     * @param index the index of the current page
     */
    private void setActionRowsUp(int index) {
        previousButton = previousButton.withDisabled(index == 0).withId(getName() + "-" + --index);
        nextButton = nextButton.withDisabled(index == pages.size() - 1).withId(getName() + "-" + ++index);

        actionRows.add(ActionRow.of(buttons));
    }
}