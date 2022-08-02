package st.networkers.discordbooks.book;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import st.networkers.discordbooks.channel.Receivable;
import st.networkers.discordbooks.component.ActionableRow;
import st.networkers.discordbooks.component.Clickable;
import st.networkers.discordbooks.message.Sendable;

import java.util.ArrayList;
import java.util.List;

public abstract class Book {
    private final String name;
    private final List<Sendable> pages = new ArrayList<>();
    private final List<ActionableRow> actionableRows = new ArrayList<>();
    private final List<Clickable> clickables = new ArrayList<>();
    private boolean publicNavigation;
    private Clickable previousClickable;
    private Clickable nextClickable;

    public Book(String name, boolean publicNavigation) {
        this.name = name;
        this.publicNavigation = publicNavigation;
    }

    public Book(String name) {
        this(name, false);
    }

    /**
     * @return the name of the book
     */
    public String getName() {
        return name;
    }

    /**
     * @return if the navigation of the book is public
     */
    public boolean isNavigationPublic() {
        return publicNavigation;
    }

    /**
     * Specifies if the book's navigation should be public or not. If not,
     * only the specified owners of the book will be able to navigate the book.
     * <p>
     * If no owners are specified, the book will automatically be public.
     *
     * @param publicNavigation if the book can be navigated by the public
     */
    public void setPublicNavigation(boolean publicNavigation) {
        this.publicNavigation = publicNavigation;
    }

    /**
     * @param index the index
     * @return the page at the index
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
    @NotNull public Sendable getPage(int index) {
        return pages.get(index);
    }

    /**
     * @return the list of pages
     */
    public List<Sendable> getPages() {
        return pages;
    }

    /**
     * Uses the provided page as the index, then adds
     * one and returns the resulting page.
     *
     * @param page the index
     * @return the next page, null if out of bounds
     */
    @Nullable public Sendable getNextPage(Sendable page) {
        int index = pages.indexOf(page);

        return index == -1 ? null : pages.get(index + 1);
    }

    /**
     * Uses the provided page as the index, then subtracts
     * one and returns the resulting page.
     *
     * @param page the index
     * @return the next page, null if out of bounds
     */
    @Nullable public Sendable getPreviousPage(Sendable page) {
        int index = pages.indexOf(page);

        return index == -1 ? null : pages.get(index - 1);
    }

    /**
     * @param pages the pages to add
     */
    public void addPages(Sendable @NotNull ... pages) {
        this.pages.addAll(List.of(pages));
    }

    /**
     * Gets the list of {@link Clickable}s.
     *
     * @return a list of action rows
     * @see #getClickables()
     * @see #getClickableRow()
     */
    private List<Clickable> getClickables() {
        return this.clickables;
    }

    /**
     * Sets the items of the {@link ActionableRow} of {@link Clickable}s.
     *
     * @param clickables the {@link Clickable}s to set
     * @see #setClickables(Clickable...)
     * @see #setNextClickable(Clickable)
     * @see #setPreviousClickable(Clickable)
     */
    public void setClickables(List<Clickable> clickables) {
        this.clickables.addAll(clickables);
    }

    /**
     * Sets the items of the {@link ActionableRow} of {@link Clickable}s.
     *
     * @param clickables the {@link Clickable}s to set
     * @see #setClickables(Clickable...)
     * @see #setNextClickable(Clickable)
     * @see #setPreviousClickable(Clickable)
     */
    public void setClickables(Clickable... clickables) {
        this.clickables.addAll(List.of(clickables));
    }

    /**
     * @return the {@link Clickable} used to navigate to the next page.
     */
    public Clickable getNextClickable() {
        return nextClickable;
    }

    /**
     * Allows you to set the base {@link Clickable} used to navigate to
     * the next page.
     * <p>
     * Some settings, such as the disabled state or ID will be overridden
     * at send time.
     *
     * @param nextClickable the base {@link Clickable}
     */
    public void setNextClickable(@NotNull Clickable nextClickable) {
        this.nextClickable = nextClickable;
    }

    /**
     * @return the {@link Clickable} used to navigate to the previous page.
     */
    public Clickable getPreviousClickable() {
        return previousClickable;
    }

    /**
     * Allows you to set the base {@link Clickable} used to navigate to
     * the previous page.
     * <p>
     * Some settings, such as the disabled state or ID will be overridden
     * at send time.
     *
     * @param previousClickable the base {@link Clickable}
     */
    public void setPreviousClickable(@NotNull Clickable previousClickable) {
        this.previousClickable = previousClickable;
    }

    /**
     * Returns an {@link ActionableRow} containing the book's
     * {@link Clickable}s, which should include the navigation {@link Clickable}.
     *
     * @return the {@link ActionableRow} of {@link Clickable}s
     */
    public ActionableRow getClickableRow() {
        return new ActionableRow(clickables);
    }

    /**
     * Adds {@link ActionableRow}s to the book.
     * <p>
     * This method should be used to add your own {@link ActionableRow}s,
     * as well as the {@link ActionableRow} of {@link Clickable}s
     * containing the navigation.
     * <p>
     * Discord allows a maximum of 5 rows per message, but this
     * library doesn't limit the number of rows by itself.
     *
     * @param actionRows the action rows to add
     */
    public void setActionableRows(ActionableRow... actionRows) {
        this.actionableRows.addAll(List.of(actionRows));
    }

    /**
     * Sends the first book page to a specified {@link Receivable}.
     *
     * @param channel the {@link Receivable} that will receive the book
     */
    public void send(@NotNull Receivable channel) {
        send(channel, 0, "0");
    }

    /**
     * Sends a given book page to a specified {@link Receivable}.
     *
     * @param channel the {@link Receivable} that will receive the book
     * @param index   the page number to send
     * @throws IndexOutOfBoundsException if the index is less than 0 or greater than the number of pages
     */
    public void send(@NotNull Receivable channel, int index) {
        send(channel, index, "0");
    }

    /**
     * Sends the first book page to a specified {@link Receivable}
     * and assigns the book's owners.
     *
     * @param channel  the {@link Receivable} that will receive the book
     * @param ownerIDs the IDs of the users who own the book
     */
    public void send(@NotNull Receivable channel, @NotNull String... ownerIDs) {
        send(channel, 0, ownerIDs);
    }

    /**
     * Sends a given book page to a specified {@link Receivable}
     * and assigns the book's owners.
     *
     * @param channel  the {@link Receivable} that will receive the book
     * @param index    the page number to send
     * @param ownerIDs the IDs of the users who own the book
     * @throws IndexOutOfBoundsException if the index is less than 0 or greater than the number of pages
     */
    public void send(@NotNull Receivable channel, int index, @NotNull String... ownerIDs) {
        Sendable sendable = pages.get(index);

        sendable.setActionableRows(actionableRows);
        buildNavigation(sendable, ownerIDs);

        channel.receive(sendable);
    }

    public Sendable edit(int index, @NotNull String... ownerIDs) {
        Sendable sendable = pages.get(index);

        sendable.setActionableRows(actionableRows);
        buildNavigation(sendable, ownerIDs);

        return sendable;
    }

    /**
     * Checks the position of the {@link Sendable} in the book
     * and changes the navigation accordingly.
     *
     * @param ownerIDs the IDs of the users who own the book
     */
    public void buildNavigation(Sendable sendable, String... ownerIDs) {
        int index = pages.indexOf(sendable);

        previousClickable = previousClickable.setDisabled(index == 0).setId(getName() + "@" + (index - 1) + "@" + String.join("#", ownerIDs));
        nextClickable = nextClickable.setDisabled(index == pages.size() - 1).setId(getName() + "@" + (index + 1) + "@" + String.join("#", ownerIDs));
    }
}
