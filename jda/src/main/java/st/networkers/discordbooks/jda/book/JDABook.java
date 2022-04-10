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
import java.util.function.UnaryOperator;

public class JDABook extends Book {
    private final List<Button> buttons = new ArrayList<>();
    private final List<ActionRow> actionRows = new ArrayList<>();
    private Button previousButton = new ButtonImpl(null, "Previous", ButtonStyle.PRIMARY, true, null);
    private Button nextButton = new ButtonImpl(null, "Next", ButtonStyle.PRIMARY, true, null);

    public JDABook(String name, boolean publicNavigation) {
        super(name, publicNavigation);
    }

    public JDABook(String name) {
        this(name, false);
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
     * @return the button used to navigate to the previous page
     */
    public Button getPreviousButton() {
        return previousButton;
    }

    /**
     * @return the button used to navigate to the next page
     */
    public Button getNextButton() {
        return nextButton;
    }

    /**
     * @param previousButtonAppearance the operator used to set the button to navigate to the previous page
     */
    public void setPreviousButtonLook(@NotNull UnaryOperator<Button> previousButtonAppearance) {
        this.previousButton = previousButtonAppearance.apply(previousButton);
    }

    /**
     * @param nextButtonAppearance the operator used to set the button to navigate to the previous page
     */
    public void setNextButtonLook(@NotNull UnaryOperator<Button> nextButtonAppearance) {
        this.nextButton = nextButtonAppearance.apply(nextButton);
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
        for (int i = 0; i < buttons.size(); i++) {
            if (buttons.get(i).equals(previousButton)) {
                previousButton = buttons.remove(i).withDisabled(index == 0).withId(getName() + "-" + --index);
                buttons.add(i, previousButton);
            } else if (buttons.get(i).equals(nextButton)) {
                nextButton = buttons.remove(i).withDisabled(index == pages.size() - 1).withId(getName() + "-" + ++index);
                buttons.add(i, nextButton);
            }
        }

        actionRows.add(ActionRow.of(buttons));
    }
}